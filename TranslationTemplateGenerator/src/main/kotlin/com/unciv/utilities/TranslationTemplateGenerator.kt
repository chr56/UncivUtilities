package com.unciv.utilities

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.unciv.utilities.processors.processUnitTypes
import com.unciv.utilities.processors.processUnits

fun main(args: Array<String>) = TranslationTemplateGenerator().main(args)

class TranslationTemplateGenerator : CliktCommand() {
    private val projectRoot by option(help = "Root path of the mod").default(System.getProperty("user.dir"))

    override fun run() {
        projectRootPath = projectRoot
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
}
