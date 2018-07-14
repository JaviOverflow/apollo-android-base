package com.apollo.base.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


fun Double.toStringPrice(): String {
    val pattern = "##0.00"
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    symbols.decimalSeparator = ','
    symbols.groupingSeparator = '.'
    val formatter = DecimalFormat(pattern, symbols)
    return formatter.format(this) + "€"
}

fun Double.toStringPercentage() = (this * 100).toStringFixedDigit(2) + "%"

fun Double.toStringPercentageWordy() = (this * 100).toStringFixedDigit(2) + "/100"

// TODO correct name, it's not fixed digit, it's fixed decimal digits
fun Double.toStringFixedDigit(digits: Int) = String.format("%.${digits}f", this)

