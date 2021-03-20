package com.yum.tummly.presentation.recipe_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.yum.tummly.R
import com.yum.tummly.domain.model.RecipeDetailsResponse
import com.yum.tummly.domain.recipe.model.Recipe
import com.yum.tummly.presentation.recipe_details.viewmodel.RecipeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


sealed class RecipeDetailsState{
    data class Recipe(val recipe : RecipeDetailsResponse) : RecipeDetailsState()
    object Error :  RecipeDetailsState()
    object Idle :  RecipeDetailsState()
}

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {


    private val viewModel by viewModels<RecipeDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.recipe_details_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }

    private fun initialize() {
//        (activity as? AppCompatActivity)?.supportActionBar?.show()
//        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
//        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
//        binding.toolbar.setNavigationOnClickListener {
//            Navigation.findNavController(binding.root).popBackStack()
//        }


        val recipe = arguments?.get("recipe") as Recipe
        lifecycleScope.launch {
            viewModel.getRecipe(recipe.id)
        }

    }

    private fun subscribe() {

        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is RecipeDetailsState.Recipe -> println("Recipe ${it.recipe}")
            }
        }

    }

}