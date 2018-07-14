package com.apollo.base.extensions

import com.apollo.base.extensions.AppendToThe.*
import java.text.Normalizer


enum class AppendToThe { Left, Center, Right }

fun String.toLength(toLength: Int, fillWith: Char = ' ', appendToThe: AppendToThe = Right): String {
    val differenceAmount = toLength - this.length

    if (differenceAmount < 0)
        return this.substring(0, toLength)

    val builder = StringBuilder()

    builder.append(if (appendToThe == AppendToThe.Left) "" else this)
    for (i in 0 until differenceAmount) { builder.append(fillWith) }
    builder.append(if (appendToThe == AppendToThe.Left) this else "")

    return builder.toString()
}

fun String.withoutAccents(): String {
    var message = this
    message = Normalizer.normalize(message, Normalizer.Form.NFD)
    message = message.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
    return message
}
