package com.example.shalawat.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shalawat")
data class ShalawatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val arabicText: String,
    val transliteration: String,
    val translation: String,
    val audioFileName: String,
    val audioSource: String, // "asset" or "internal"
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
