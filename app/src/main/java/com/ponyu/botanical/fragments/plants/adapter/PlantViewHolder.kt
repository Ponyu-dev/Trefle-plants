package com.ponyu.botanical.fragments.plants.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ponyu.botanical.databinding.ViewHolderPlantDetailBinding
import com.ponyu.botanical.ui.PlantItemUiState
import com.ponyu.botanical.util.ext.executeWithAction

class PlantViewHolder (
    private val binding: ViewHolderPlantDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemUiState: PlantItemUiState){
        binding.executeWithAction {
            this.plantItemUiState = itemUiState
        }
    }
}
