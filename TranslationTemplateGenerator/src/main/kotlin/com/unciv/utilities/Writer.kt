package com.unciv.utilities

fun translationLine(key: String, translation: String? = null): String =
    if (!translation.isNullOrBlank())
        "$key = $translation"
    else
        "$key = " // template


@JvmInline
value class CommentGrass(val value: String) {
    companion object {
        val Large = CommentGrass("###################")
        val Medium = CommentGrass("#########")
        val Small = CommentGrass("#####")
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
fun commentBlock(title: String, size: CommentGrass = CommentGrass.Medium, content: () -> String): String {
    return buildString {
        appendLine("${size.value} $title [Start] ${size.value}")
        appendLine(content())
        appendLine("${size.value} $title [End]   ${size.value}")
    }
}


fun StringBuilder.commentBlock(
    title: String,
    size: CommentGrass = CommentGrass.Medium,
    content: StringBuilder.() -> Unit
) {
    appendLine("${size.value} $title [Start] ${size.value}")
    content()
    appendLine("${size.value} $title [End]   ${size.value}")
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
fun StringBuilder.commentList(title: String, size: CommentGrass = CommentGrass.Medium, strings: () -> List<String>) {
    commentBlock(title, size) {
        for (string in strings()) {
            append(string)
        }
    }
}
