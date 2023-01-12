package com.unciv.utilities.processors

import com.unciv.utilities.RuleSet
import com.unciv.utilities.RuleSets
import com.unciv.utilities.models.Unit
import com.unciv.utilities.translationLine

fun generateUnitLine(unit: Unit, translation: String? = null): String = with(unit) {
    buildString {
        //
        // comment
        //
        if (uniqueTo != null && replaces != null) {
            // just renaming
            appendLine("## $replaces of `$uniqueTo`:")
        } else {
            appendLine("##")
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
    for (unit in units) {
         print(generateUnitLine(unit))
    }
}


