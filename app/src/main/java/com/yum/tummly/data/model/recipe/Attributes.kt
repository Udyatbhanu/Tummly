package com.yum.tummly.data.model.recipe


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attributes(
    @Json(name = "course")
    var course: List<String> = listOf(),
    @Json(name = "cuisine")
    var cuisine: List<String> = listOf(),
    @Json(name = "holiday")
    var holiday: List<String> = listOf()
)