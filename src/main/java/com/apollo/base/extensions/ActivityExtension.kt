package com.apollo.base.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager


fun <A> Activity.navigateTo(target: Class<A>) {
    val intent = Intent(this, target)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK);
    this.startActivity(intent)
}

fun Activity.hideKeyboard() {
    currentFocus.doIfNotNull { focus ->
        val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(focus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
