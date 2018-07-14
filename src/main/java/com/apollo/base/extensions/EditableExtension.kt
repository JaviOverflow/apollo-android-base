package com.apollo.base.extensions

import android.text.Editable


fun Editable.toDouble(): Double =
        this.toString()
            .replace(',', '.')
            .toDoubleOrNull() ?: 0.0

fun Editable.toInteger(): Int =
        this.toString()
            .toIntOrNull() ?: 0
