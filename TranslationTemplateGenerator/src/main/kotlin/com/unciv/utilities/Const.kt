package com.unciv.utilities

import java.io.File


lateinit var projectRootPath: String

fun ruleSetDir(): File = File(projectRootPath, "jsons")
fun outputDir(): File = File(projectRootPath, "output")