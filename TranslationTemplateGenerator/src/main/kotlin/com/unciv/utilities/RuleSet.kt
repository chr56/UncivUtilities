package com.unciv.utilities

import com.unciv.utilities.models.Unit as ModelsUnit
import com.unciv.utilities.models.UnitType as ModelsUnitType


const val FILENAME_UNIT = "Units"
const val FILENAME_UNIT_TYPE = "UnitTypes"

enum class RuleSet(val fileName: String, val type: Class<*>) {
    Unit(FILENAME_UNIT, ModelsUnit::class.java),
    UnitType(FILENAME_UNIT_TYPE, ModelsUnitType::class.java);
}

object RuleSets {
    val allFileNames = RuleSet.values().map { it.fileName }

    fun byFileName(fileName: String): RuleSet =
        when (fileName) {
            FILENAME_UNIT -> RuleSet.Unit
            FILENAME_UNIT_TYPE -> RuleSet.UnitType
            else -> throw Exception("Unknown rule $fileName")
        }

    private val ruleSetStore: MutableMap<RuleSet, List<Model>> = mutableMapOf()

    fun getRuleSet(type: RuleSet): List<Model> =
        ruleSetStore[type] ?: throw Exception("RuleSet $type hadn't register yet!")

    fun registerRuleSet(type: RuleSet, list: List<Model>) {
        ruleSetStore[type] = list
    }
}

