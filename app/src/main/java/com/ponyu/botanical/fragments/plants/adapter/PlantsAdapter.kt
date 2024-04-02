package com.ponyu.botanical.fragments.plants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.databinding.DataBindingUtil.inflate
import com.ponyu.botanical.R
import com.ponyu.botanical.databinding.ViewHolderPlantDetailBinding
import com.ponyu.botanical.ui.PlantItemUiState

class PlantsAdapter (
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<PlantItemUiState, PlantViewHolder>(Comparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PlantViewHolder{
        val binding = inflate<ViewHolderPlantDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.view_holder_plant_detail,
            parent,
            false
        )

        return PlantViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(viewHolderPlant: PlantViewHolder, position: Int) {
        getItem(position)?.let { plantItemUiState -> viewHolderPlant.bind(plantItemUiState) }
    }

    object Comparator : DiffUtil.ItemCallback<PlantItemUiState>() {
        override fun areItemsTheSame(oldItem: PlantItemUiState, newItem: PlantItemUiState): Boolean {
            return oldItem.getId() == newItem.getId()
        }

        override fun areContentsTheSame(
            oldItem: PlantItemUiState,
            newItem: PlantItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }
}