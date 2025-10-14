package com.harbdm.urbanexplorer.core.data.repository

import android.content.Context
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.core.net.toUri
import com.harbdm.urbanexplorer.core.model.Resource
import com.harbdm.urbanexplorer.core.domain.repository.PhotoStorageRepository
import java.io.File
import java.io.IOException

/**
 * Default implementation of [com.harbdm.urbanexplorer.core.domain.repository.PhotoStorageRepository] that saves photo file into
 * app's internal storage.
 *
 * Responsibilities cycle:
 * 1. Takes a file's from temporary location and copies to app-owned location.
 * 2. Deleting temporary uri from caching location.
 * 3. Providing stable Uri `content://` URI for the permanent file.
 */
class PhotoStorageRepositoryImpl(
   private val context: Context
) : PhotoStorageRepository {

    /**
     * Saves temporary photo into app storage.
     *
     * @param [tempUriString] Uri string for the temporary file location.
     * @return [Resource.Success] with new, permanent file location.
     * @return [Resource.Error] with any exception in case of error.
     */
    override suspend fun savePhoto(tempUriString: String): Resource<String> =
        //file operations are blocking, so using [Dispatchers.IO]
        withContext(Dispatchers.IO) {
            try {
                val tempUri = tempUriString.toUri()

                val destinationFile =
                    File(context.filesDir, "IMG_${System.currentTimeMillis()}.jpg")

                context.contentResolver.openInputStream(tempUri)?.use { input ->
                    destinationFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                } ?: throw IOException("Could not open input stream for URI: $tempUriString")

                // If temporal photo was created by our app in app's cache location
                // we should delete that to get free space back.
                if (tempUri.authority == "${context.packageName}.provider") {
                    context.contentResolver.delete(tempUri, null, null)
                }

                val permanentUri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    destinationFile
                )

                Resource.Success(permanentUri.toString())

            } catch (e: Exception) {
                Resource.Error(e)
            }
        }
}