package com.apollo.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast


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

    override fun showMessageToUser(message: String) =
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}