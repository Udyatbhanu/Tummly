package com.yum.tummly.data.model.recipe_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetRecipeDetailsResponse(
    @Json(name = "attributes")
    var attributes: Attributes? = Attributes(),
    @Json(name = "attribution")
    var attribution: Attribution? = Attribution(),
    @Json(name = "flavors")
    var flavors: Map<String, Double>? = mapOf(),
    @Json(name = "id")
    var id: String = "",
    @Json(name = "images")
    var images: List<Image> = listOf(),
    @Json(name = "ingredientLines")
    var ingredientLines: List<String> = listOf(),
    @Json(name = "name")
    var name: String? = "",
    @Json(name = "numberOfServings")
    var numberOfServings: Int? = 0,
    @Json(name = "nutritionEstimates")
    var nutritionEstimates: List<NutritionEstimate>? = listOf(),
    @Json(name = "rating")
    var rating: Int? = 0,
    @Json(name = "source")
    var source: Source? = Source(),
    @Json(name = "totalTime")
    var totalTime: String? = "",
    @Json(name = "totalTimeInSeconds")
    var totalTimeInSeconds: Int? = 0,
    @Json(name = "yield")
    var servings: String? = ""
)