package com.yum.tummly.data.model.recipe_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "sourceDisplayName")
    var sourceDisplayName: String = "",
    @Json(name = "sourceRecipeUrl")
    var sourceRecipeUrl: String = "",
    @Json(name = "sourceSiteUrl")
    var sourceSiteUrl: String = ""
)