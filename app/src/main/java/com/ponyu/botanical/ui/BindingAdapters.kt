package com.ponyu.botanical.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ponyu.botanical.R

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.image_not_found)
        .fitCenter()
        .into(this)
}