package com.yum.tummly.data.repository

import com.yum.tummly.data.api.Config
import com.yum.tummly.data.api.RecipeApi
import com.yum.tummly.data.model.recipe.GetRecipesResponse
import com.yum.tummly.data.model.recipe_details.GetRecipeDetailsResponse
import com.yum.tummly.data.utils.BaseRepository
import com.yum.tummly.data.utils.ResultWrapper
import javax.inject.Inject


class RecipeRepository @Inject constructor(private val api: RecipeApi) : BaseRepository() {

    suspend fun searchRecipe(
        query: String,
        start: Int,
        maxResult: Int
    ): ResultWrapper<GetRecipesResponse> =
        invoke { api.searchRecipes(Config.APP_ID, Config.APP_KEY, query, start, maxResult) }

    suspend fun getRecipe(id: String): ResultWrapper<GetRecipeDetailsResponse> =
        invoke { api.getRecipe(Config.APP_ID, Config.APP_KEY, id) }
}