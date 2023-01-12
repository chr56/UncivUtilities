package com.unciv.utilities

import com.unciv.utilities.parsers.UnitParser
import com.unciv.utilities.parsers.UnitTypeParser
import io.github.xn32.json5k.Json5
import java.io.File

/**
 * kotlinx serialization [Json5]
 */
val JsonParser = Json5 {
    //ignoreUnknownKeys = true
}

interface Model

interface Parser<T : Model> {
    fun parse(file: File): List<T>
}

object Parsers {
    fun parse(file: File, ruleSet: RuleSet): List<Model> {
        val parser = when (ruleSet) {
            RuleSet.Unit -> UnitParser()
            RuleSet.UnitType -> UnitTypeParser()
            else -> throw Exception("No Parser for $ruleSet")
        }
        return parser.parse(file)
    }
}