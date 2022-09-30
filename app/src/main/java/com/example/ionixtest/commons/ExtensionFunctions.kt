package com.example.ionixtest.commons

import android.media.Image
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.ionixtest.R

fun String?.orAlternative(alternative: String) = this ?: alternative

fun ViewBinding.glideConfig(urlImage: String, host: ImageView) {
    Glide.with(this.root.context)
        .load(urlImage)
        .placeholder(R.drawable.place_holder_image)
        .fitCenter()
        .into(host)
}