package com.harbdm.urbanexplorer.domain.repository

import com.harbdm.urbanexplorer.core.model.Resource

interface PhotoStorageRepository {

    suspend fun savePhoto(tempUriString: String): Resource<String>
}