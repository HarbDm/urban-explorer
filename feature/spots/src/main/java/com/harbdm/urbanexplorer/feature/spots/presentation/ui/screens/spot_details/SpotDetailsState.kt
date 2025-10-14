package com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.spot_details

import com.harbdm.urbanexplorer.core.domain.model.Spot
import com.harbdm.urbanexplorer.core.ui.model.SpotTypeUi
import com.harbdm.urbanexplorer.core.ui.model.SpotTypeUiProvider

data class SpotDetailsState (
    val spot: Spot? = null,
    val spotType: SpotTypeUi = SpotTypeUiProvider.default()
)