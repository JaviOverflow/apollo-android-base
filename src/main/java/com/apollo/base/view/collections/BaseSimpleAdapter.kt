package com.apollo.base.view.collections

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.apollo.base.data.models.ModelWithMetadata
import com.apollo.base.view.Bindable


class BaseSimpleAdapter<Model, ItemView>(createNewItemView: () -> ItemView) : BaseAdapter()
        where ItemView : ViewGroup, ItemView : Bindable<Model>, Model : ModelWithMetadata {

    private val factory: () -> ItemView = createNewItemView
    private var items: List<Model>


    fun sync(items: List<Model>) {
        this.items = items
        notifyDataSetChanged()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val itemView = view as? ItemView ?: factory()
        itemView.bind(items[position])
        return itemView
    }

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = items[position].id.hashCode().toLong()

    override fun getCount(): Int = items.size

    init {
        this.items = listOf()
    }
}