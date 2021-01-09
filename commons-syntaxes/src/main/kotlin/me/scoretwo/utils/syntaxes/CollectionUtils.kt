package me.scoretwo.utils.syntaxes

import java.util.ArrayList

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}