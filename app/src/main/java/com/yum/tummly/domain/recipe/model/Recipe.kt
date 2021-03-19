package com.yum.tummly.domain.recipe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(val recipeName : String, val imageUrl : String, val timeInSeconds: Int?): Parcelable