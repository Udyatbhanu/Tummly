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
private const val STARTING_OFFSET = 0
private const val OFFSET_LIMIT = 500

class SearchRecipesUseCaseImpl @Inject constructor(private val recipeRepository: RecipeRepository) :
    SearchRecipesUseCase {

    private val _cache = mutableListOf<Recipe>()
    private val recipes = MutableSharedFlow<Recipes>(replay = 1)

    private var isRequestInProgress = false
    private var nextOffSet = STARTING_OFFSET
    private var isEnd = false


    private fun calculateNextOffset(resultCount: Int) {
        isEnd = _cache.size >= resultCount
        nextOffSet += PAGE_SIZE
    }

    override suspend fun requestMore(query: String) {
        if (isRequestInProgress || isEnd || nextOffSet >= OFFSET_LIMIT) return
        requestData(query)
    }


    private fun getDisplayImageUrl(imageMap: Map<String, String>): String {
        if (imageMap.isNotEmpty()) {
            return imageMap.iterator().next().value
        }
        return ""
    }

    private suspend fun requestData(query: String) {
        isRequestInProgress = true

        try {
            when (val response =
                recipeRepository.searchRecipe(query, nextOffSet, PAGE_SIZE)) {
                is ResultWrapper.Success -> {
                    //Ref: com.yum.tummly.data.model.recipe_details.GetRecipeDetailsResponse
                    val recipesMapped = response.value.matches.map {
                        Recipe(
                            id = it.id,
                            recipeName = it.recipeName,
                            imageUrl = getDisplayImageUrl(it.imageUrlsBySize),
                            timeInSeconds = it.totalTimeInSeconds
                        )
                    }
                    _cache.addAll(recipesMapped)
                    println("_cache size ${_cache.size}")
                    calculateNextOffset(response.value.totalMatchCount)
                    recipes.emit(Recipes.Success(_cache))
                }

            }
            isRequestInProgress = false
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


    /**
     * Clear the list
     */
    override suspend fun clear() {
        _cache.clear()
        recipes.emit(Recipes.Success(_cache))
    }


    companion object {
        private const val PAGE_SIZE = 50
    }

}