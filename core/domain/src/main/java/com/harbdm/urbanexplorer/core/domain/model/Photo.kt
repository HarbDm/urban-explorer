package com.harbdm.urbanexplorer.core.domain.model

data class Photo(
    val photoId: Long,
    val spotOwnerId: Long,
    val uriString: String,
    val caption: String?
)