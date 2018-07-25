package com.apollo.base.view.collections

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.apollo.base.extensions.doIfNotNull
import com.apollo.base.view.Bindable


class BaseRecyclerAdapter<Model, ItemView>(private val factory: () -> ItemView)
    : RecyclerView.Adapter<ConcreteViewHolder<Model, ItemView>>()
        where ItemView : Bindable<Model>, ItemView : View {

    var items: List<Model> = listOf()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    var onItemClickConsumer: ((Model) -> Unit)? = null

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ConcreteViewHolder<Model, ItemView>, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.view.setOnClickListener {
            onItemClickConsumer.doIfNotNull {
                it(holder.item!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ConcreteViewHolder<Model, ItemView> =
            ConcreteViewHolder(factory())

    infix fun onItemClick(consumer: (Model) -> Unit) {
        onItemClickConsumer = consumer
    }
}