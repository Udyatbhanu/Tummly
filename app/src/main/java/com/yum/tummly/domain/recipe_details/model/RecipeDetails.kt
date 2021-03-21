package com.yum.tummly.domain.recipe_details.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flavor(val flavor: String, val scale: Double) : Parcelable

@Parcelize
data class RecipeDetails(
    val yield: String?,
    val ingredientLines: List<String>?,
    val flavors: List<Flavor>?,
    val image: String
) : Parcelable