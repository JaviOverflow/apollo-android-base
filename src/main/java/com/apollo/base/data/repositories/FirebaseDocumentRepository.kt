package com.apollo.base.data.repositories


import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.apollo.base.data.models.ModelWithMetadata
import com.apollo.base.extensions.timestampNow
import io.reactivex.Observable


class FirebaseDocumentRepository<T : ModelWithMetadata>(
        path: List<String>,
        private val model: Class<T>
) {

    private var documentReference: DocumentReference

    private var onChangeListener: ((Observable<T>) -> Unit)? = null

    private var snapshotRegistration: ListenerRegistration? = null

    init {
        documentReference = FirebaseFirestore.getInstance().collection(path[0]).document(path[1])
        for (i in 2..path.lastIndex step 2)
            documentReference = documentReference.collection(path[i]).document(path[i + 1])
    }

    fun subscribe(onChange: (Observable<T>) -> Unit) {
        this.onChangeListener = onChange
        this.snapshotRegistration = documentReference.addSnapshotListener { documentSnapshots, error ->
            val items = documentSnapshots.toObject(this.model)
            onChangeListener?.invoke(Observable.just(items))
        }
    }

    fun unsubscribe() {
        this.onChangeListener = null
        this.snapshotRegistration?.remove()
    }

    fun put(item: T) {
        item.updatedAt = timestampNow()
        documentReference.set(item)
    }
}
