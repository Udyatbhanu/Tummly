package com.yum.tummly.data.model.recipe


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Criteria(
    @Json(name = "allowedIngredient")
    var allowedIngredient: Any? = null,
    @Json(name = "excludedIngredient")
    var excludedIngredient: Any? = null,
    @Json(name = "q")
    var q: String = ""
)