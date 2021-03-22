package com.yum.tummly.presentation.recipe.viewmodel

import androidx.lifecycle.*
import com.yum.tummly.domain.model.Recipes
import com.yum.tummly.domain.recipe.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val searchRecipesUseCase: SearchRecipesUseCase) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    val state : LiveData<Recipes> = queryLiveData.switchMap { query ->
        liveData {
            val recipes = searchRecipesUseCase.recipeSearchResultsAsStream(query).asLiveData()
            emitSource(recipes)
        }
    }

    fun searchRecipes(query: String) {
        queryLiveData.postValue(query)
    }

    fun listScrolled() {
        viewModelScope.launch {
            val query = queryLiveData.value
            searchRecipesUseCase.requestMore(query!!)
        }

    }


    fun clear(){
        viewModelScope.launch {
            searchRecipesUseCase.clear()
        }
    }
}