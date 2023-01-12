package com.unciv.utilities

import java.io.File


lateinit var projectRoot: String

fun ruleSetDir(): File = File(projectRoot, "jsons")
fun outputDir(): File = File(projectRoot, "output")