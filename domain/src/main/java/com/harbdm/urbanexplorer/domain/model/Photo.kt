package com.harbdm.urbanexplorer.domain.model

data class Photo(
    val photoId: Long,
    val spotOwnerId: Long,
    val uriString: String,
    val caption: String?
)