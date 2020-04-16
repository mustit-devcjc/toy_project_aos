package dev.chu.toyapp.etc.extensions

val Any.TAG: String
    get() = this::class.simpleName ?: this.toString()