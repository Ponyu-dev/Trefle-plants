package com.ponyu.botanical.fragments.plants.adapter

import androidx.recyclerview.widget.DiffUtil

object PlantsDiffCallback : DiffUtil.ItemCallback<PlantItem>() {
    override fun areItemsTheSame(oldItem: PlantItem, newItem: PlantItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PlantItem, newItem: PlantItem): Boolean {
        return oldItem == newItem
    }
}