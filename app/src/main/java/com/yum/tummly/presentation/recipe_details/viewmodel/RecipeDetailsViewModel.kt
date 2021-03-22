package com.yum.tummly.presentation.recipe_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yum.tummly.domain.model.RecipeDetailsResponse
import com.yum.tummly.domain.recipe_details.GetRecipeDetailsUseCase
import com.yum.tummly.presentation.recipe_details.ui.RecipeDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase) :
    ViewModel() {
    private val _state = MutableLiveData<RecipeDetailsState>().apply { RecipeDetailsState.Idle }

    val state: LiveData<RecipeDetailsState>
        get() = _state

    suspend fun getRecipe(id: String) {
        _state.value = RecipeDetailsState.Loading
        getRecipeDetailsUseCase.getRecipeAsStream(id).collect {
            it
            when (it) {
                is RecipeDetailsResponse.Success -> _state.value =
                    RecipeDetailsState.Recipe(it.data)
                is RecipeDetailsResponse.Error -> _state.value = RecipeDetailsState.Error
                else -> _state.value = RecipeDetailsState.Idle
            }
        }


    }

    private fun handlerIntent() {
        viewModelScope.launch {

        }

    }
}