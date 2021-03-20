package com.yum.tummly.domain.model

import com.yum.tummly.domain.recipe_details.model.RecipeDetails

sealed class RecipeDetailsResponse {
    data class Success(val data: RecipeDetails) : RecipeDetailsResponse()
    data class Error(val error: Exception) : RecipeDetailsResponse()
}