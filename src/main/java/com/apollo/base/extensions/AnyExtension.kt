package com.apollo.base.extensions

fun <T : Any> T?.doIfNotNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}

fun <T : Any> T?.doIfNull(f: () -> Unit) {
    if (this == null) f()
}
