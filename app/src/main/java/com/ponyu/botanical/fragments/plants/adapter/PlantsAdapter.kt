package com.ponyu.botanical.fragments.plants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ponyu.botanical.R

class PlantsAdapter (
    private val onClick: (Int) -> Unit
)  : ListAdapter<PlantItem, PlantViewHolder>(PlantsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder = PlantViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_plant_detail, parent, false),
        onClick
    )

    override fun onBindViewHolder(viewHolderPlant: PlantViewHolder, position: Int) {
        viewHolderPlant.bind(getItem(position))
    }
}