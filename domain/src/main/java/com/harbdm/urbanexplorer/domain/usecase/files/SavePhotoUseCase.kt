package com.harbdm.urbanexplorer.domain.usecase.files

import com.harbdm.urbanexplorer.core.model.Resource
import com.harbdm.urbanexplorer.domain.repository.PhotoStorageRepository

/**
 * Add a photo to the internal storage.
 *
 * This use case take a temporal photo's Uri and ensures that it will be
 * saved into internal app storage to not loose it. Validates temp uri to not be blank.
 *
 * @param tempUriString Uri of the photo to save.
 * @return [Resource.Success] with new permanent Uri of the photo.
 * @throws [Exception] if temporal uri is blank.
 */
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