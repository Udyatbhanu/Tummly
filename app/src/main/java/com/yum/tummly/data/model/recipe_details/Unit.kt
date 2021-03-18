package com.yum.tummly.data.model.recipe_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Unit(
    @Json(name = "abbreviation")
    var abbreviation: String = "",
    @Json(name = "decimal")
    var decimal: Boolean = false,
    @Json(name = "id")
    var id: String = "",
    @Json(name = "name")
    var name: String = "",
    @Json(name = "plural")
    var plural: String = "",
    @Json(name = "pluralAbbreviation")
    var pluralAbbreviation: String = ""
)