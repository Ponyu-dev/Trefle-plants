package com.ponyu.botanical.fragments.plants.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ponyu.botanical.R

class PlantViewHolder (
    private val view: View,
    private val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val imagePlant: ImageView by lazy { itemView.findViewById(R.id.imagePlant) }
    private val textViewScientificName: TextView by lazy { itemView.findViewById(R.id.textViewScientificName) }
    private val textViewCommonName: TextView by lazy { itemView.findViewById(R.id.textViewCommonName) }

    fun bind(
        plantItem: PlantItem
    ) {
        textViewScientificName.text = plantItem.scientificName
        textViewCommonName.text = plantItem.commonName

        Glide.with(view.context)
            .load(plantItem.imageUrl)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_not_found)
            .fitCenter()
            .into(imagePlant)

        view.setOnClickListener {
            onClick(plantItem.idPlant)
        }
    }
}