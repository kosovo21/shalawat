package com.example.shalawat.data.audio

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioFileManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val audioDir: File
        get() = File(context.filesDir, "audio").also { it.mkdirs() }

    /**
     * Copies an audio file from the given SAF [uri] into internal storage.
     * Returns the generated filename (UUID-based to avoid collisions).
     */
    fun copyAudioFile(uri: Uri): String {
        val extension = context.contentResolver.getType(uri)
            ?.substringAfter("/")
            ?.let { ".$it" }
            ?: ""
        val fileName = "${UUID.randomUUID()}$extension"
        val destinationFile = File(audioDir, fileName)

        context.contentResolver.openInputStream(uri)?.use { input ->
            destinationFile.outputStream().use { output ->
                input.copyTo(output)
            }
        } ?: throw IllegalStateException("Unable to open input stream for URI: $uri")

        return fileName
    }

    /**
     * Deletes an audio file from internal storage.
     */
    fun deleteAudioFile(fileName: String) {
        val file = File(audioDir, fileName)
        if (file.exists()) {
            file.delete()
        }
    }

    /**
     * Returns a [File] reference for the given audio file in internal storage.
     */
    fun getAudioFile(fileName: String): File {
        return File(audioDir, fileName)
    }
}
