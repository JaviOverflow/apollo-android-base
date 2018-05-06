package com.beepflow.pos.lib.view.collections

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class SuperConcreteViewHolder<out ItemView>(val view: ItemView) : RecyclerView.ViewHolder(view)
        where ItemView : ViewGroup