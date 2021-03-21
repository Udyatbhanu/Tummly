package com.yum.tummly.presentation.recipe_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.yum.tummly.R
import com.yum.tummly.databinding.RecipeDetailsFragmentBinding
import com.yum.tummly.domain.recipe.model.Recipe
import com.yum.tummly.domain.recipe_details.model.RecipeDetails
import com.yum.tummly.presentation.recipe_details.viewmodel.RecipeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


sealed class RecipeDetailsState {
    data class Recipe(val details: RecipeDetails) : RecipeDetailsState()
    object Error : RecipeDetailsState()
    object Idle : RecipeDetailsState()
}

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: RecipeDetailsFragmentBinding
    private val ingredientsAdapter : IngredientsAdapter = IngredientsAdapter()
    private val flavorsAdapter : FlavorsAdapter = FlavorsAdapter()

    private val viewModel by viewModels<RecipeDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding =
            DataBindingUtil.inflate(inflater, R.layout.recipe_details_fragment, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }

    private fun initialize() {
        requireActivity().actionBar?.hide()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_action_name)
        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
        val recipe = arguments?.get("recipe") as Recipe
        lifecycleScope.launch {
            viewModel.getRecipe(recipe.id)
        }
        // Set the header image
        Glide.with(binding.root.context)
            .asBitmap()
            .load(recipe.imageUrl)

            .into(binding.image)


        binding.ingredients.adapter = ingredientsAdapter
        binding.flavors.adapter = flavorsAdapter


    }

    private fun subscribe() {

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is RecipeDetailsState.Recipe -> {
                    with(it.details){
                        ingredientsAdapter.submitList(ingredientLines?.toMutableList())
                        flavorsAdapter.submitList(flavors?.toMutableList())
                        binding.details = it.details
                    }

                }

            }
        }

    }

}