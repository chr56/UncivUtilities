package com.unciv.utilities.models

import com.unciv.utilities.Model
import kotlinx.serialization.Serializable

//todo

@Serializable
data class Civilopedia(
    val text: String
) : Model