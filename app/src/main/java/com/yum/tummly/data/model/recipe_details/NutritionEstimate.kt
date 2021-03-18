package com.yum.tummly.data.model.recipe_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NutritionEstimate(
    @Json(name = "attribute")
    var attribute: String = "",
    @Json(name = "description")
    var description: String = "",
    @Json(name = "unit")
    var unit: Unit = Unit(),
    @Json(name = "value")
    var value: Double = 0.0
)