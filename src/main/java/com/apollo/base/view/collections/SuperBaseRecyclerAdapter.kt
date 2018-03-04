package com.jj.pos.lib.view.collections

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT


class SuperBaseRecyclerAdapter<Model, ItemView>(
        private val context: Context,
        private val layoutId: Int,
        private val onBind: ((ItemView, Model) -> Unit)?)
    : RecyclerView.Adapter<SuperConcreteViewHolder<ItemView>>()
        where ItemView : ViewGroup {

    var items: List<Model> = listOf()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    var onItemClickListener: ((Model) -> Unit)? = null

    var onItemLongClickListener: ((Model) -> Unit)? = null

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SuperConcreteViewHolder<ItemView>, position: Int) {
        val item = items[position]
        onBind?.invoke(holder.view, item)

        holder.view.setOnClickListener { onItemClickListener?.invoke(item) }
        holder.view.setOnLongClickListener { onItemLongClickListener?.invoke(item); true }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SuperConcreteViewHolder<ItemView> {
        val view = LayoutInflater.from(context).inflate(layoutId, null) as ViewGroup
        view.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        val holder = SuperConcreteViewHolder(view as ItemView)
        return holder
    }

    infix fun onItemClick(consumer: (Model) -> Unit) {
        onItemClickListener = consumer
    }

    infix fun onItemLongClick(consumer: (Model) -> Unit) {
        onItemLongClickListener = consumer
    }
}
