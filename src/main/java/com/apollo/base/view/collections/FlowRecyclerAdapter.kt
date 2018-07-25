package com.apollo.base.view.collections

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT


class FlowRecyclerAdapter<Model>(
        private val context: Context,
        private val layoutId: Int,
        private val onBind: ((ViewGroup, Model) -> Unit))
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class FlowViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view)

    var items: List<Model> = listOf()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    var onItemClickListener: ((Model) -> Unit)? = null

    var onItemLongClickListener: ((Model) -> Unit)? = null

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        onBind.invoke(holder.itemView as ViewGroup, item)

        holder.itemView.setOnClickListener { onItemClickListener?.invoke(item) }
        holder.itemView.setOnLongClickListener { onItemLongClickListener?.invoke(item); true }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FlowViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutId, null) as ViewGroup
        view.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        return FlowViewHolder(view)
    }

    infix fun onItemClick(consumer: (Model) -> Unit) {
        onItemClickListener = consumer
    }

    infix fun onItemLongClick(consumer: (Model) -> Unit) {
        onItemLongClickListener = consumer
    }
}
