package com.yuriyyangel.dictionary_feature.utils


fun String.convertToPhoneticFormat(): String {
    val startIndex = 1
    val endIndex = this.length - 1
    return "[${this.substring(startIndex, endIndex)}]"
}