package com.unciv.utilities

fun translationLine(key: String, translation: String? = null): String =
    if (!translation.isNullOrBlank())
        "$key = $translation"
    else
        "$key = " // template


private const val commentTitleFormat = " %7s %-7s "
private fun commentTitle(title: String, length: Int = 28): Array<String> {
    val start = String.format(commentTitleFormat, title, "[Start]")
    val end = String.format(commentTitleFormat, title, "[End]")
    return if (start.length < length) {
        val topPadding = (length - start.length) / 2
        val bottomPadding = (length - end.length) / 2
        val top = "#".repeat(topPadding)
        val bottom = "#".repeat(bottomPadding)
        arrayOf("$top$start$top", "$bottom$end$bottom")
    } else {
        // too long
        val top = "#".repeat(length / 4)
        val bottom = "#".repeat(length / 4)
        arrayOf("$top$start$top", "$bottom$end$bottom")
    }
}

/**
 * generate
 * ```
 * ############ <title> [Start]##############
 * (content)
 * ############ <title> [End]  ##############
 * ```
 */
fun commentBlock(
    title: String,
    length: Int = 22,
    content: String
): String {
    val wrapper = commentTitle(title, length)
    return buildString {
        appendLine(wrapper[0])
        appendLine(content)
        appendLine(wrapper[1])
    }
}

/**
 * generate
 * ```
 * ############ <title> [Start]##############
 * (content)
 * ############ <title> [End]  ##############
 * ```
 */
fun StringBuilder.commentBlock(
    title: String,
    length: Int = 22,
    content: StringBuilder.() -> Unit
) {
    val wrapper = commentTitle(title, length)
    appendLine(wrapper[0])
    content()
    appendLine(wrapper[1])
}


/**
 * Generate
 * ```
 * ############ <title> [Start]##############
 * (content 1)
 * (content 2)
 * ...
 * ############ <title> [End]  ##############
 * ```
 */
fun StringBuilder.commentList(
    title: String,
    size: Int = 22,
    strings: () -> List<String>
) {
    commentBlock(title, size) {
        for (string in strings()) {
            append(string)
        }
    }
}
