package com.unciv.utilities

import com.unciv.utilities.processors.processUnitTypes
import com.unciv.utilities.processors.processUnits

fun main(args: Array<String>) {
    projectRoot = args[0]
    println("=====  Unciv Mod Utility  =====")
    println(
        "Current Project Root: $projectRoot"
    )

    updateRuleSetStore()

    processUnitTypes()
    processUnits()
}

fun updateRuleSetStore() {
    val allJsonFiles = ruleSetDir().listFiles { file -> file.isFile && file.extension == "json" }
        ?: throw Exception("Can not find json files!")

    for (file in allJsonFiles) {
        val name = file.nameWithoutExtension
        if (name in RuleSets.allFileNames) { // available
            val ruleSet = RuleSets.byFileName(name)
            val result = Parsers.parse(file, ruleSet)
            RuleSets.registerRuleSet(ruleSet, result)
        }
    }
}