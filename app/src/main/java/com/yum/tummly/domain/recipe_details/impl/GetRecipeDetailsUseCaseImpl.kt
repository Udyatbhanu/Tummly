package com.yum.tummly.domain.recipe_details.impl

import com.yum.tummly.data.repository.RecipeRepository
import com.yum.tummly.data.utils.ResultWrapper
import com.yum.tummly.domain.model.RecipeDetailsResponse
import com.yum.tummly.domain.model.Recipes
import com.yum.tummly.domain.recipe.model.Recipe
import com.yum.tummly.domain.recipe_details.GetRecipeDetailsUseCase
import com.yum.tummly.domain.recipe_details.model.Flavor
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


    /**
     *
     */
    private fun mapFlavors(flavorsMap : Map<String, Double>) : List<Flavor>{

        var flavors = ArrayList<Flavor>()
        flavorsMap.forEach{
            flavors.add(Flavor(it.key, it.value))
        }
        return flavors

    }

    private suspend fun fetchRecipeDetails(id: String){
        try{
            when(val response = recipeRepository.getRecipe(id)){
                is ResultWrapper.Success -> {
                    //Ref: com.yum.tummly.data.model.recipe.GetRecipesResponse
                    with(response.value){
                        val details = RecipeDetails(yield = servings?:"",
                            ingredientLines = ingredientLines,
                            flavors = mapFlavors(flavors?: mapOf<String, Double>()),
                            image = images[0].hostedLargeUrl,
                            numberOfServings = numberOfServings.toString())

                        recipe.emit(RecipeDetailsResponse.Success(details))
                    }


                }
            }

        }catch(exception : IOException){
            recipe.emit(RecipeDetailsResponse.Error(exception))
        }catch(exception : HttpException){
            recipe.emit(RecipeDetailsResponse.Error(exception))
        }

    }
}