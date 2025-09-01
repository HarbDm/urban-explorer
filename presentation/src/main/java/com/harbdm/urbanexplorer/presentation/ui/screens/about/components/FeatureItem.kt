package com.harbdm.urbanexplorer.presentation.ui.screens.about.components

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.ui.res.stringResource
import com.harbdm.urbanexplorer.presentation.R

data class FeatureItem(
    val tittle: String,
    val body: String,
    @DrawableRes val iconRes: Int
)


/**
 * Provider of predefined app features. If needed in future consider moving them to
 * db/wherever and getting them from there!
 */
object FeaturesItemsProvider {

    fun getFeatures(context: Context?) = listOf(
        FeatureItem(
            tittle = context?.getString(R.string.about_menu_title_1) ?: "",
            body = context?.getString(R.string.about_menu_body_1) ?: "",
            iconRes = R.drawable.ic_camera
        ),
        FeatureItem(
            tittle = context?.getString(R.string.about_menu_title_2) ?: "",
            body = context?.getString(R.string.about_menu_body_2) ?: "",
            iconRes = R.drawable.ic_gallery
        ),
        FeatureItem(
            tittle = context?.getString(R.string.about_menu_title_3) ?: "",
            body = context?.getString(R.string.about_menu_body_3) ?: "",
            iconRes = R.drawable.ic_scissors
        )
    )
}