package com.yum.tummly.data.model.recipe_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attribution(
    @Json(name = "html")
    var html: String = "",
    @Json(name = "logo")
    var logo: String = "",
    @Json(name = "text")
    var text: String = "",
    @Json(name = "url")
    var url: String = ""
)