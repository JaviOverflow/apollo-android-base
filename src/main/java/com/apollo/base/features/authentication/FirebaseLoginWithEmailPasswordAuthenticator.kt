package com.apollo.base.features.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.apollo.base.data.models.User


class FirebaseLoginWithEmailPasswordAuthenticator : BaseAuthenticator {

    val firebaseUser: FirebaseUser?
        get() = FirebaseAuth.getInstance().currentUser

    override fun login(user: String, password: String, onSuccess: ((User) -> Unit)?, onFailure: (() -> Unit)?) {
        try {
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(user, password)
                    .addOnSuccessListener { loadUser(onSuccess) }
                    .addOnFailureListener { onFailure?.invoke() }
        } catch (_: IllegalArgumentException) {
            onFailure?.invoke()
        }
    }

    fun loadUser(onSuccess: ((User) -> Unit)?) {
        FirebaseFirestore.getInstance()
                .collection("users").document(firebaseUser!!.uid)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject(User::class.java)
                    onSuccess?.invoke(user)
                }
    }

    override fun isLogged() = firebaseUser != null

    override fun logout() = FirebaseAuth.getInstance().signOut()
}