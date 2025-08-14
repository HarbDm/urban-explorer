package com.harbdm.urbanexplorer.data.repository

import android.content.Context
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.core.net.toUri
import com.harbdm.urbanexplorer.core.model.Resource
import com.harbdm.urbanexplorer.domain.repository.PhotoStorageRepository
import java.io.File
import java.io.IOException

class PhotoStorageRepositoryImpl(
   private val context: Context
) : PhotoStorageRepository {
    override suspend fun savePhoto(tempUriString: String): Resource<String> =
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