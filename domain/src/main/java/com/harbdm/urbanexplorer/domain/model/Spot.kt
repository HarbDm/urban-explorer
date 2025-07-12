package com.harbdm.urbanexplorer.domain.model

data class Spot(
    val spotName: String,
    val spotType: String,
    val spotDescription: String? = null,
    val locationHint: String,
    val spotRating: Int,
    val id: Long = 0,
    val timeStamp: Int,
    val photos: List<Photo> = emptyList()
)

class InvalidSpotException(message: String): Exception(message)