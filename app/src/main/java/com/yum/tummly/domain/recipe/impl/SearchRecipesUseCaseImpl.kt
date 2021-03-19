package com.yum.tummly.domain.recipe.impl

import com.yum.tummly.data.repository.RecipeRepository
import com.yum.tummly.data.utils.ResultWrapper
import com.yum.tummly.domain.model.Recipes
import com.yum.tummly.domain.recipe.SearchRecipesUseCase
import com.yum.tummly.domain.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


/**
 * This class holds the business logic for searching a repository and also keeps track oof the pagination
 */
private const val STARTING_PAGE_INDEX = 0

class SearchRecipesUseCaseImpl @Inject constructor(private val recipeRepository: RecipeRepository) :
    SearchRecipesUseCase {

    private val _cache = mutableListOf<Recipe>()
    private val recipes = MutableSharedFlow<Recipes>(replay = 1)

    private var isRequestInProgress = false
    private var lastRequestedPage = STARTING_PAGE_INDEX


    private suspend fun requestData(query: String) {
        isRequestInProgress = true

        try {
            when (val response =
                recipeRepository.searchRecipe(query, lastRequestedPage, PAGE_SIZE)) {
                is ResultWrapper.Success -> {
                    val recipesMapped = response.value.matches.map {
                        Recipe(
                            recipeName = it.recipeName,
                            imageUrl = it.imageUrlsBySize.iterator().next().value,
                            it.totalTimeInSeconds
                        )
                    }
                    _cache.addAll(recipesMapped)
                    recipes.emit(Recipes.Success(_cache))
                }

            }
        } catch (exception: IOException) {
            recipes.emit(Recipes.Error(exception))
        } catch (exception: HttpException) {
            recipes.emit(Recipes.Error(exception))
        }


    }

    override suspend fun recipeSearchResultsAsStream(query: String): Flow<Recipes> {
        requestData(query)
        return recipes
    }

    override suspend fun requestMore(query: String) {
    }

    companion object {
        private const val PAGE_SIZE = 50
    }

}