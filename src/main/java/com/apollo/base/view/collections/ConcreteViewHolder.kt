package com.apollo.base.view.collections

import android.support.v7.widget.RecyclerView
import android.view.View
import com.apollo.base.view.Bindable

class ConcreteViewHolder<Model, ModelLayout>(val view: ModelLayout) : RecyclerView.ViewHolder(view), Bindable<Model>
        where ModelLayout : View, ModelLayout : Bindable<Model> {

    var item: Model? = null

    override fun bind(item: Model) {
        this.item = item
        view.bind(item)
    }
}