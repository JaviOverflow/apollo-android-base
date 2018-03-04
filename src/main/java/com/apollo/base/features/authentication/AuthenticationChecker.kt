package com.apollo.base.features.authentication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@SuppressLint("Registered")
class AuthenticationChecker(private val context: Context,
                            private val loginActivity: Class<*>) {

    var user: FirebaseUser? = null

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null)
            goToLoginActivity()
        else
            user = currentUser

    }

    private fun goToLoginActivity() {
        val intent = Intent(context, loginActivity)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        goToLoginActivity()
    }
}
