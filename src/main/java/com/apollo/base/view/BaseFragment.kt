package com.apollo.base.view

import android.app.Fragment
import android.content.Context


open class BaseFragment<A, P> : Fragment()
        where A : BaseActivity<*>, P : BasePresenter {

    protected var activity: A? = null
    protected val presenter: P?
        get() = activity?.presenter as P?

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity == null && context is BaseActivity<*>) {
            activity = context as? A
        }
    }

}