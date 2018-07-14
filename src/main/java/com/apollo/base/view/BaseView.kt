package com.apollo.base.view

import android.support.design.widget.Snackbar


interface BaseView {

    fun showMessageToUser(message: String, durationInSeconds: Int = Snackbar.LENGTH_LONG)
}