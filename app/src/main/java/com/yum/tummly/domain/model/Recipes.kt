package com.yum.tummly.domain.model

import com.yum.tummly.domain.recipe.model.Recipe

sealed class Recipes {
    data class Success(val data: List<Recipe>) : Recipes()
    data class Error(val error: Exception) : Recipes()
}