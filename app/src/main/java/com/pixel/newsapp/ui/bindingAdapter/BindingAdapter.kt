package com.pixel.newsapp.ui.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pixel.newsapp.R

@BindingAdapter("imageUrl")
fun loadImageFromUrl(
    imageView: ImageView,
    imageUrl: String?,
) {
    Glide
        .with(imageView)
        .load(imageUrl)
        .placeholder(R.drawable.ic_logo)
        .into(imageView)
}
