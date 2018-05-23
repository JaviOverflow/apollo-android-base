package com.apollo.base.extensions


fun <E> List<E>?.isNullOrEmpty(): Boolean {
    var isMatching = false
    this.doIfNull { isMatching = true }
    this.doIfNotNull { isMatching = it.isEmpty() }
    return isMatching
}
