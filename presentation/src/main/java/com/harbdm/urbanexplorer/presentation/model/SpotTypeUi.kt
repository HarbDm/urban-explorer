package com.harbdm.urbanexplorer.presentation.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.DisabledVisible
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector
import com.harbdm.urbanexplorer.presentation.R

data class SpotTypeUi(
    val key: String,
    @StringRes val nameRes: Int,
    val icon: ImageVector
)

object SpotTypeUiProvider {

    private val types = listOf(
        SpotTypeUi("unknown", R.string.type_unknown, Icons.Default.DisabledVisible),
        SpotTypeUi("cafe", R.string.type_cafe, Icons.Default.LocalCafe),
        SpotTypeUi("restaurant", R.string.type_restaurant, Icons.Default.Restaurant),
        SpotTypeUi("graffiti", R.string.type_graffiti, Icons.Default.Brush),
        SpotTypeUi("park", R.string.type_park, Icons.Default.NaturePeople),
        SpotTypeUi("museum", R.string.type_museum, Icons.Default.Museum),
        SpotTypeUi("landmark", R.string.type_landmark, Icons.Default.Landscape),
        SpotTypeUi("other", R.string.type_other, Icons.Default.MoreHoriz),
    )

    fun fromString(type: String): SpotTypeUi =
        types.firstOrNull { it.key == type } ?: types[0]

    fun all(): List<SpotTypeUi> = types.drop(1)
    fun default(): SpotTypeUi = types[0]
}