package com.apollo.base.view.collections

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.BaseAdapter


class FlowListAdapter<Model>(
        private val context: Context,
        private val layoutId: Int,
        private val onBind: ((ViewGroup, Model) -> Unit))
    : BaseAdapter()
        where Model: Any {

    var items: List<Model> = listOf()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    var onItemClick: ((Model) -> Unit)? = null
    var onItemLongClick: ((Model) -> Unit)? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflateNewView()
        onBind.invoke(view as ViewGroup, items[position])
        view.setOnClickListener { onItemClick?.invoke(items[position]) }
        view.setOnLongClickListener { onItemLongClick?.invoke(items[position]); true }
        return view
    }

    private fun inflateNewView(): ViewGroup {
        val view = LayoutInflater.from(context).inflate(layoutId, null) as ViewGroup
        view.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        return view
    }

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = items[position].hashCode().toLong()

    override fun getCount(): Int = items.size

}
