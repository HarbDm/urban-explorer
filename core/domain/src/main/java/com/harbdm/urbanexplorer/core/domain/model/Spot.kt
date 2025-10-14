package com.harbdm.urbanexplorer.core.domain.model

data class Spot(
    val spotName: String,
    val spotType: String,
    val spotDescription: String? = null,
    val locationHint: String,
    val spotRating: Int,
    val id: Long = 0,
    val timeStamp: Long,
    val photos: List<Photo> = emptyList()
)

//custom exception to let VM know that spot info incorrect
class InvalidSpotException(message: String): Exception(message)