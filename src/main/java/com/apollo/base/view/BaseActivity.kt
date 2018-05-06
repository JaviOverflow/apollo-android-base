package com.apollo.base.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.apollo.base.R


abstract class BaseActivity<P> : AppCompatActivity(), BaseView where P : BasePresenter {

    abstract val layoutId: Int

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showMessageToUser(message: String) {
        val snackbar = Snackbar.make(this.window.decorView, message, Snackbar.LENGTH_LONG)
        snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text).textSize = 18f
        snackbar.show()
    }

}