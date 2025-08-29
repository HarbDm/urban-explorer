package com.harbdm.urbanexplorer.presentation.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

/**
 * Adapter needed to map icons we provide from resources
 * to icons in imageView through app:srcCompat.
 */
@BindingAdapter("app:srcCompat")
fun setSrcCompat(imageView: ImageView, @DrawableRes drawableRes: Int){
    imageView.setImageResource(drawableRes)
}