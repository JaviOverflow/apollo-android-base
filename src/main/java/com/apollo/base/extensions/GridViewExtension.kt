package com.apollo.base.extensions

import android.widget.GridView

fun <T> GridView.setOnItemClickListener(listener: (T) -> Unit) {
    this.setOnItemClickListener { parent, view, position, id ->
        val item = this.adapter.getItem(position)
        listener(item as T)
    }
}

fun <T> GridView.setOnItemLongClickListener(listener: (T) -> Unit) {
    this.setOnItemLongClickListener { parent, view, position, id ->
        val item = this.adapter.getItem(position)
        listener(item as T)
        true
    }
}
