package com.yum.tummly.presentation.recipe.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.yum.tummly.R
import com.yum.tummly.databinding.RecipeRowBinding
import com.yum.tummly.domain.recipe.model.Recipe

class RecipeViewHolder(private val binding: RecipeRowBinding):  RecyclerView.ViewHolder(binding.root) {
    fun bind(item : Recipe){
        with(binding){
            Glide.with(root.context)
                .asBitmap()
                .load(item.imageUrl)
                .apply(
                    RequestOptions().transform(CenterCrop(), RoundedCorners(16))
                    .placeholder(R.drawable.common_full_open_on_phone)
                    .error(R.drawable.common_full_open_on_phone))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.recipeImageView)
            recipe = item
        }

    }
}