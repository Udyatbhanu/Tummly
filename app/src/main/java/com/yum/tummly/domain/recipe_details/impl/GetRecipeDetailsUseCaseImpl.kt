package com.yum.tummly.domain.recipe_details.impl

import com.yum.tummly.data.repository.RecipeRepository
import com.yum.tummly.data.utils.ResultWrapper
import com.yum.tummly.domain.model.RecipeDetailsResponse
import com.yum.tummly.domain.model.Recipes
import com.yum.tummly.domain.recipe.model.Recipe
import com.yum.tummly.domain.recipe_details.GetRecipeDetailsUseCase
import com.yum.tummly.domain.recipe_details.model.RecipeDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.yield
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipeDetailsUseCaseImpl  @Inject constructor(private val recipeRepository: RecipeRepository) : GetRecipeDetailsUseCase {

    private val recipe = MutableSharedFlow<RecipeDetailsResponse>(replay = 1)

    override suspend fun getRecipeAsStream(id: String): Flow<RecipeDetailsResponse> {
        fetchRecipeDetails(id)
        return recipe
    }

    private suspend fun fetchRecipeDetails(id: String){
        try{
            when(val response = recipeRepository.getRecipe(id)){
                is ResultWrapper.Success -> {
                    //Ref: com.yum.tummly.data.model.recipe_details.GetRecipeDetailsResponse
                    val details = RecipeDetails(yield = response.value.servings?:"",
                        ingredientLines = response.value.ingredientLines,
                        flavors = response.value.flavors,
                        image = response.value.images[0].hostedMediumUrl)
                    recipe.emit(RecipeDetailsResponse.Success(details))
                }
            }

        }catch(exception : IOException){
            recipe.emit(RecipeDetailsResponse.Error(exception))
        }catch(exception : HttpException){
            recipe.emit(RecipeDetailsResponse.Error(exception))
        }

    }
}