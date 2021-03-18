package com.yum.tummly.data.model.recipe


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetRecipesResponse(
    @Json(name = "attribution")
    var attribution: Attribution = Attribution(),
    @Json(name = "criteria")
    var criteria: Criteria = Criteria(),
    @Json(name = "matches")
    var matches: List<Matche> = listOf(),
    @Json(name = "totalMatchCount")
    var totalMatchCount: Int = 0
)