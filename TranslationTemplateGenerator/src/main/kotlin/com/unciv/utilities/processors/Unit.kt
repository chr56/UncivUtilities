package com.unciv.utilities.processors

import com.unciv.utilities.*
import com.unciv.utilities.models.Unit
import com.unciv.utilities.models.UnitType

fun generateUnitLine(unit: Unit, translation: String? = null): String = with(unit) {
    buildString {
        //
        // comment
        //
        if (uniqueTo != null && replaces != null) {
            // just renaming
            appendLine("## `$replaces` of `$uniqueTo`:")
        } else {
            //appendLine("##")
        }
        //
        // actual translation
        //
        append(translationLine(unit.name, translation))
        appendLine()
    }
}


fun processUnits() {
    val units = RuleSets.getRuleSet(RuleSet.Unit) as List<Unit>
    val unitTypes = RuleSets.getRuleSet(RuleSet.UnitType) as List<UnitType>

    //
    // sort by UnitType.MovementType
    //
    val rest = units.toMutableList()

    val land = mutableListOf<Unit>()
    val water = mutableListOf<Unit>()
    val air = mutableListOf<Unit>()

    val landTypes = unitTypes.filter { it.isLand() }.map { it.name }
    val waterTypes = unitTypes.filter { it.isWater() }.map { it.name }
    val airTypes = unitTypes.filter { it.isAir() }.map { it.name }
    for (unit in units) {
        when (unit.unitType) {
            in landTypes -> {
                land.add(unit)
                rest.remove(unit)
            }

            in waterTypes -> {
                water.add(unit)
                rest.remove(unit)
            }

            in airTypes -> {
                air.add(unit)
                rest.remove(unit)
            }

            else -> println("Warning: $unit has unknown type ${unit.unitType}")
        }
    }

    //
    // sort by UnitType
    //
    val landBucket = categoryByUnitType(land)
    val waterBucket = categoryByUnitType(water)
    val airBucket = categoryByUnitType(air)

    buildString {
        commentBlock("Land", 56) {
            for (group in landBucket) {
                commentList(group.key, 34) { group.value.map { generateUnitLine(it) } }
            }
        }
        commentBlock("Water", 56) {
            for (group in waterBucket) {
                commentList(group.key, 34) { group.value.map { generateUnitLine(it) } }
            }
        }
        commentBlock("Air", 56) {
            for (group in airBucket) {
                commentList(group.key, 34) { group.value.map { generateUnitLine(it) } }
            }
        }
    }.also { println(it) }
}

fun categoryByUnitType(units: List<Unit>): Map<String, MutableList<Unit>> {
    val bucket = mutableMapOf<String, MutableList<Unit>>()
    for (unit in units) {
        val category = bucket[unit.unitType]
        if (category == null) {
            bucket[unit.unitType] = mutableListOf(unit)
        } else {
            category.add(unit)
        }
    }
    return bucket
}


