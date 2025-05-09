package com.harbdm.urbanexplorer.domain.model

data class Spot(
    val spotName: String,
    val spotDescription: String,
    val locationHint: String,
    val spotRating: Int,
    val id: Long = 0,
    val timeStamp: Int,
    val photos: List<Photo> = emptyList()
)