package com.yum.tummly.presentation.recipe_details.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yum.tummly.R
import com.yum.tummly.databinding.FlavorsItemBinding
import com.yum.tummly.domain.recipe_details.model.Flavor

class FlavorsAdapter : ListAdapter<Flavor, FlavorsAdapter.FlavorsViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavorsViewHolder {
        val binding: FlavorsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.flavors_item,
            parent, false
        )
        return FlavorsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FlavorsViewHolder, position: Int) {
        val flavor = getItem(position)
        if (flavor != null) {
            holder.bind(flavor)
        }
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Flavor>() {
            override fun areItemsTheSame(
                oldItem: Flavor,
                newItem: Flavor
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: Flavor,
                newItem: Flavor
            ): Boolean =
                oldItem == newItem
        }
    }


    class FlavorsViewHolder(private val binding: FlavorsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Flavor) {

            with(binding) {
                flavor = item
            }
        }

    }


}