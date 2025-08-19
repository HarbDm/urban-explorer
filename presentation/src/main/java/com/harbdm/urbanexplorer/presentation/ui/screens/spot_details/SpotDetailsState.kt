package com.harbdm.urbanexplorer.presentation.ui.screens.spot_details

import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUi
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUiProvider

data class SpotDetailsState (
    val spot: Spot? = null,
    val spotType: SpotTypeUi = SpotTypeUiProvider.default()
)