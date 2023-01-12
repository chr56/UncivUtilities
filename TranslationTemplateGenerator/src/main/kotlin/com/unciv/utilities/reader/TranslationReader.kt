package com.unciv.utilities.reader

import java.io.File

private val charset = Charsets.UTF_8
private const val SEPARATOR = " = "

private fun parseLocalizationFile(file: File): LinkedHashMap<String, String> =
    LinkedHashMap<String, String>().apply {
        file.reader(charset).forEachLine { line ->
            if (line.contains(SEPARATOR)) line.split(SEPARATOR).also { result ->
                if (result.size == 2) {
                    val key = result[0].replace("\\n", "\n")
                    val value = result[1].replace("\\n", "\n")
                    this[key] = value
                }
            }
        }
    }