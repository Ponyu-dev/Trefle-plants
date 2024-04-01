package com.ponyu.botanical.fragments.plant_detail.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ponyu.botanical.R
import com.ponyu.botanical.data.remote.plant.ImagesData
import com.ponyu.botanical.databinding.PlantItemImageBinding

class PlantImageAdapter(
    private val images: List<ImagesData?>,
    private val onClick: (ImagesData) -> Unit
) : RecyclerView.Adapter<PlantImageAdapter.PlantImageViewHolder>() {
    inner class PlantImageViewHolder(
        private val binding: PlantItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(image: ImagesData) {
            Glide.with(binding.root.context)
                .load(image.imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_not_found)
                .fitCenter()
                .into(binding.imageView)

            binding.root.setOnClickListener { onClick(image) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantImageViewHolder {
        val binding = PlantItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantImageViewHolder, position: Int) {
        images[position]?.let { holder.bind(it) }
    }

    override fun getItemCount() = images.size
}

data class PlantImage(val url: String)