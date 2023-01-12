package com.unciv.utilities.parsers

import com.unciv.utilities.JsonParser
import com.unciv.utilities.Parser
import com.unciv.utilities.models.UnitType
import kotlinx.serialization.decodeFromString
import java.io.File

class UnitTypeParser : Parser<UnitType> {
    override fun parse(file: File): List<UnitType> {
        val text = file.readText(Charsets.UTF_8)
        return JsonParser.decodeFromString(text)
    }
}