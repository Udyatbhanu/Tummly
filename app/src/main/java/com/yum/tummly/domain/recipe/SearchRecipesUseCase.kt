package com.yum.tummly.domain.recipe

import com.yum.tummly.domain.model.Recipes
import kotlinx.coroutines.flow.Flow

interface SearchRecipesUseCase {

    suspend fun recipeSearchResultsAsStream(query : String): Flow<Recipes>
    suspend fun clear()
    suspend fun requestMore(query : String)

}