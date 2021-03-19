package com.yum.tummly.data.model.recipe


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Matche(
    @Json(name = "attributes")
    var attributes: Attributes = Attributes(),
    @Json(name = "flavors")
    var flavors: Any? = null,
    @Json(name = "id")
    var id: String = "",
    @Json(name = "imageUrlsBySize")
    var imageUrlsBySize: Map<String, String> = mapOf<String, String>(),
    @Json(name = "ingredients")
    var ingredients: List<String> = listOf(),
    @Json(name = "rating")
    var rating: Int = 0,
    @Json(name = "recipeName")
    var recipeName: String = "",
    @Json(name = "smallImageUrls")
    var smallImageUrls: List<String> = listOf(),
    @Json(name = "sourceDisplayName")
    var sourceDisplayName: String = "",
    @Json(name = "totalTimeInSeconds")
    var totalTimeInSeconds: Int? = null
)