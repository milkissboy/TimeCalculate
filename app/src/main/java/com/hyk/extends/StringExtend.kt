package com.hyk.extends

private const val inner_empty = ""
private const val inner_space = " "

val String.Companion.empty: String
    get() = inner_empty

val String.Companion.space: String
    get() = inner_space