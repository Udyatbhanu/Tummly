package com.yum.tummly.domain.recipe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(val id: String, val recipeName : String, val imageUrl : String, val time: String?): Parcelable