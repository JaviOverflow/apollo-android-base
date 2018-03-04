package com.apollo.base.view.widgets

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet


class BaseRecyclerView @JvmOverloads constructor(
        context: Context?,
        attributeSet: AttributeSet? = null,
        defStyle: Int = 0
) : RecyclerView(context, attributeSet, defStyle) {

    init {
        this.layoutManager = LinearLayoutManager(context)
        this.itemAnimator = DefaultItemAnimator()
    }
}
