package com.unciv.utilities.parsers

import com.unciv.utilities.JsonParser
import com.unciv.utilities.Parser
import com.unciv.utilities.models.Unit
import kotlinx.serialization.decodeFromString
import java.io.File


class UnitParser : Parser<Unit> {
    override fun parse(file: File): List<Unit> {
        val text = file.readText(Charsets.UTF_8)
        return JsonParser.decodeFromString(text)
    }
}
