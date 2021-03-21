package com.yum.tummly.presentation.recipe_details.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yum.tummly.R
import com.yum.tummly.databinding.IngredientsRowBinding

class IngredientsAdapter : ListAdapter<String, IngredientsAdapter.IngredientsViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding : IngredientsRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.ingredients_row,
            parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = getItem(position)
        if(ingredient != null){
            holder.bind(ingredient)
        }
    }



    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean =
                oldItem == newItem
        }
    }


    class IngredientsViewHolder(private val binding : IngredientsRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : String){
            binding.ingredient.text = item

        }
    }

}