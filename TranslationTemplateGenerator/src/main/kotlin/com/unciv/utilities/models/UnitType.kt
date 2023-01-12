package com.unciv.utilities.models

import com.unciv.utilities.Model
import kotlinx.serialization.Serializable

@Serializable
data class UnitType(
    val name: String,
    val movementType: String,
    val uniques: List<String>? = null
) : Model {
    enum class MovementType {
        Land, Water, Air;

        fun from(string: String): MovementType =
            when (string) {
                "Land" -> Land
                "Water" -> Water
                "Air" -> Air
                else -> throw IllegalArgumentException("Unknown MovementType $string")
            }
    }
}