package com.yum.tummly.data.model.recipe_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flavors(
    @Json(name = "Bitter")
    var bitter: Double = 0.0,
    @Json(name = "Meaty")
    var meaty: Double = 0.0,
    @Json(name = "Piquant")
    var piquant: Double = 0.0,
    @Json(name = "Salty")
    var salty: Double = 0.0,
    @Json(name = "Sour")
    var sour: Double = 0.0,
    @Json(name = "Sweet")
    var sweet: Double = 0.0
)