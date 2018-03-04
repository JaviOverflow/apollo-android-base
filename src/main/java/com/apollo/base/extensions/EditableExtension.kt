package com.apollo.base.extensions

import android.text.Editable


fun Editable.toDouble() = java.lang.Double.valueOf(this.toString().replace(',', '.'))

fun Editable.toInteger() = Integer.valueOf(this.toString())
