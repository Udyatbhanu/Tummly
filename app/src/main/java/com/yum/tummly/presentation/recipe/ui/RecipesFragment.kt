package com.yum.tummly.presentation.recipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yum.tummly.R
import com.yum.tummly.databinding.FragmentRecipesBinding
import com.yum.tummly.domain.model.Recipes
import com.yum.tummly.presentation.recipe.viewmodel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private lateinit var binding: FragmentRecipesBinding
    private val viewModel by viewModels<RecipesViewModel>()
    private val recipeListAdapter : RecipeListAdapter = RecipeListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipes, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }

    private fun initialize() {
        with(binding.recipeSearchBar) {
            queryHint = resources.getString(R.string.search_placeholder)
            isIconified = false
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(searchQuery: String?): Boolean {
                    val query = searchQuery?: DEFAULT_QUERY
                    viewModel.searchRecipes(query)
                    return false
                }

                override fun onQueryTextChange(searchQuery: String?): Boolean {
                    if(searchQuery.isNullOrEmpty()){
                        viewModel.clear()
                    }

                    return false
                }


            })
        }
        binding.recipeList.adapter = recipeListAdapter
        setupScrollListener()

    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner){result ->

            when(result){
                is Recipes.Success -> {
                    recipeListAdapter.submitList(result.data.toMutableList())
                }
            }
        }
    }


    /**
     *
     */
    private fun setupScrollListener() {
        val layoutManager = binding.recipeList.layoutManager as LinearLayoutManager
        binding.recipeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                binding.fab.visibility = View.VISIBLE
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (visibleItemCount + lastVisibleItem + 5 >= totalItemCount) {

                    viewModel.listScrolled()
                }
            }
        })
    }

    companion object {
        private const val DEFAULT_QUERY = "Baked Caramel"
    }
}