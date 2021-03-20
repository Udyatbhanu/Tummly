package com.yum.tummly.domain.recipe_details.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDetails(
    val yield: String?,
    val ingredientLines: List<String>?,
    val flavors: Map<String, Double>?,
    val image: String
) : Parcelable