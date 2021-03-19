package com.yum.tummly.presentation.recipe.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yum.tummly.R
import com.yum.tummly.databinding.RecipeRowBinding
import com.yum.tummly.domain.recipe.model.Recipe

class RecipeListAdapter: ListAdapter<Recipe, RecipeViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding : RecipeRowBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recipe_row,
            parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
       val recipe = getItem(position)
        if(recipe != null){
            holder.bind(recipe)
        }
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(
                oldItem: Recipe,
                newItem: Recipe
            ): Boolean =
                oldItem.recipeName == newItem.recipeName

            override fun areContentsTheSame(
                oldItem: Recipe,
                newItem: Recipe
            ): Boolean =
                oldItem == newItem
        }
    }


}