package com.unciv.utilities.models

import com.unciv.utilities.Model
import kotlinx.serialization.Serializable

@Serializable
data class Unit(
    val name: String,
    val unitType: String,

    val uniqueTo: String? = null,
    val replaces: String? = null,

    val cost: Int = -1,
    val movement: Int = -1,
    val strength: Int = -1,
    val rangedStrength: Int = -1,
    val range: Int = -1,
    val interceptRange: Int = -1,
    val requiredTech: String? = null,
    val obsoleteTech: String? = null,
    val requiredResource: String? = null,
    val upgradesTo: String? = null,
    val hurryCostModifier: Int = -1,
    val promotions: List<String>? = null,
    val uniques: List<String>? = null,
    val replacementTextForUniques: String? = null,
    val attackSound: String? = null,
    val civilopediaText: List<Civilopedia>? = null,
    val religiousStrength: Int = -1
) : Model