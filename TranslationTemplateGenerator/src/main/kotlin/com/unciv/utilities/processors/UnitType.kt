package com.unciv.utilities.processors

import com.unciv.utilities.RuleSet
import com.unciv.utilities.RuleSets
import com.unciv.utilities.models.UnitType

fun processUnitTypes() {
    val unitTypes = RuleSets.getRuleSet(RuleSet.UnitType) as List<UnitType>
    for (unitType in unitTypes) {
        println("${unitType.name} (${unitType.movementType})")
    }
}
