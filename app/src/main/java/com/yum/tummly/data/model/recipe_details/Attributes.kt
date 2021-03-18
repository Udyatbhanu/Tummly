package com.yum.tummly.data.model.recipe_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attributes(
    @Json(name = "course")
    var course: List<String> = listOf()
)