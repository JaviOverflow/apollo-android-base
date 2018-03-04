package com.apollo.base.data.repositories


import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.firestore.Query.Direction.*
import com.apollo.base.data.models.ModelWithMetadata
import com.apollo.base.extensions.timestampNow
import com.apollo.base.extensions.doIfNotNull
import com.apollo.base.extensions.doIfNull
import io.reactivex.Observable


class FirebaseCollectionRepository<T : ModelWithMetadata>(
        path: List<String>,
        private val model: Class<T>
) {
    private var collectionReference: CollectionReference

    private var filtering: ((Query) -> Query)? = null
    private var onSuccessListener: ((Observable<List<T>>) -> Unit)? = null

    private var registration: ListenerRegistration? = null

    init {
        collectionReference = FirebaseFirestore.getInstance().collection(path.first())
        for (i in 1..path.lastIndex step 2)
            collectionReference = collectionReference.document(path[i]).collection(path[i + 1])
    }

    fun get(filter: ((Query) -> Query)?): Observable<List<T>> {
        this.filtering = filter
        return Observable.create { emitter ->
            createQuery().get()
                    .addOnSuccessListener { documentSnapshots ->
                        if (emitter.isDisposed) return@addOnSuccessListener
                        val items = documentSnapshots.toObjects(this.model)
                        emitter.onNext(items)
                        emitter.onComplete()
                    }
                    .addOnFailureListener { exception ->
                        Log.d("GET Exception", exception.message)
                    }
        }
    }

    fun subscribe(onChange: (Observable<List<T>>) -> Unit
            /**, filter: ((Query) -> Query)? = null**/
    ) {
        this.onSuccessListener = onChange
//        this.filtering = filter
        registration?.remove()
        registration = createQuery().addSnapshotListener { documentSnapshots, error ->
            error.doIfNull { processAndEmitSnapshot(documentSnapshots) }
        }
    }

    fun unsubscribe() {
        this.onSuccessListener = null
        this.registration?.remove()
    }

    private fun createQuery(): Query {
        var query = collectionReference.orderBy("updatedAt", DESCENDING)
        this.filtering.doIfNotNull { query = it.invoke(query) }
        return query
    }

    private fun processAndEmitSnapshot(documentSnapshots: QuerySnapshot) {
        val items = documentSnapshots.toObjects(this.model)
        onSuccessListener?.invoke(Observable.just(items))
    }

    fun post(item: T) {
        collectionReference.document(item.id).set(item)
    }

    fun put(item: T) {
        item.updatedAt = timestampNow()
        collectionReference.document(item.id).set(item)
    }

    fun delete(item: T) {
        collectionReference.document(item.id).delete()
    }
}
