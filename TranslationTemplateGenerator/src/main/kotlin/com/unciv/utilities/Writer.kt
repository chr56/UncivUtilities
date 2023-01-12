package com.unciv.utilities

fun translationLine(key: String, translation: String? = null): String =
    if (!translation.isNullOrBlank())
        "$key = $translation"
    else
        "$key = " // template