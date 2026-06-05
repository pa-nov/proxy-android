package com.panov.proxy.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi

object ExportManager {
    const val MIME_TYPE_TEXT = "text/plain"
    const val MIME_TYPE_JSON = "application/json"

    fun copyToClipboard(context: Context, text: String, label: String) {
        context.getSystemService(ClipboardManager::class.java).setPrimaryClip(
            ClipData.newPlainText(label, text)
        )
    }

    @RequiresApi(29)
    fun saveToDownloads(context: Context, text: String, file: String, type: String) {
        val resolver = context.contentResolver

        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.MIME_TYPE, type)
            put(MediaStore.MediaColumns.DISPLAY_NAME, file)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }

        val row = resolver.insert(
            MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY), values
        ) ?: return

        resolver.openOutputStream(row)?.use {
            it.write(text.toByteArray(Charsets.UTF_8))
        }

        values.apply {
            clear()
            put(MediaStore.MediaColumns.IS_PENDING, 0)
        }

        resolver.update(row, values, null, null)
    }
}