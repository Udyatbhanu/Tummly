package com.yum.tummly.data.model.recipe_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "hostedLargeUrl")
    var hostedLargeUrl: String = "",
    @Json(name = "hostedMediumUrl")
    var hostedMediumUrl: String = "",
    @Json(name = "hostedSmallUrl")
    var hostedSmallUrl: String = "",
    @Json(name = "imageUrlsBySize")
    var imageUrlsBySize: Any = mapOf<String,String>()
)