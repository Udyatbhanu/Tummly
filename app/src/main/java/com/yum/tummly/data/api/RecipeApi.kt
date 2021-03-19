package com.yum.tummly.data.api

import com.yum.tummly.data.model.recipe.GetRecipesResponse
import com.yum.tummly.data.model.recipe_details.GetRecipeDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET(Endpoints.SEARCH_RECIPES)
    suspend fun searchRecipes(
        @Query("_app_id") _app_id: String,
        @Query("_app_key") _app_key: String,
        @Query("q") searchQuery: String,
        @Query("start") start: Int,
        @Query("maxResult") item_per_page: Int
    ): GetRecipesResponse

    @GET(Endpoints.RECIPE_DETAILS)
    suspend fun getRecipe(
        @Query("_app_id") _app_id: String,
        @Query("_app_key") _app_key: String,
        @Path("id") id: String,
    ): GetRecipeDetailsResponse
}