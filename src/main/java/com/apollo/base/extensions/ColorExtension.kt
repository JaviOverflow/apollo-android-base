package com.apollo.base.extensions


private val materialColors = listOf(0xFFAED581, 0xFFFFB74D, 0xFF64B5F6, 0xFFE57373, 0xFF4DB6AC, 0xFF90A4AE, 0xFFBA68C8)

fun Int.toMaterialColor() = materialColors[this % materialColors.size].toInt()