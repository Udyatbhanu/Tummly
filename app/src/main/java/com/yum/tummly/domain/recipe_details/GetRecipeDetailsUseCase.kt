package com.yum.tummly.domain.recipe_details

import com.yum.tummly.domain.model.RecipeDetailsResponse
import com.yum.tummly.domain.recipe_details.model.RecipeDetails
import kotlinx.coroutines.flow.Flow

interface GetRecipeDetailsUseCase {

    suspend fun getRecipeAsStream(id: String) : Flow<RecipeDetailsResponse>
}