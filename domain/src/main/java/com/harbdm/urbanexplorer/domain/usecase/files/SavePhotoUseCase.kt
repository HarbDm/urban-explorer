package com.harbdm.urbanexplorer.domain.usecase.files

import com.harbdm.urbanexplorer.core.model.Resource
import com.harbdm.urbanexplorer.domain.repository.PhotoStorageRepository

class SavePhotoUseCase(
    private val photoStorageRepository: PhotoStorageRepository
) {
    suspend operator fun invoke(tempUriString: String): Resource<String> {
        if(tempUriString.isBlank()) {
            throw Exception("Temporary URI string can't be blank.")
        }
        return photoStorageRepository.savePhoto(tempUriString)
    }
}