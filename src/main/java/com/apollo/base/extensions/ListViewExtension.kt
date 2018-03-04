package com.apollo.base.extensions

import android.widget.ListView

fun <T> ListView.setOnItemClickListener(listener: (T) -> Unit) {
    this.setOnItemClickListener { parent, view, position, id ->
        val item = this.adapter.getItem(position)
        listener(item as T)
    }
}

fun <T> ListView.setOnItemLongClickListener(listener: (T) -> Unit) {
    this.setOnItemLongClickListener { parent, view, position, id ->
        val item = this.adapter.getItem(position)
        listener(item as T)
        true
    }
}
