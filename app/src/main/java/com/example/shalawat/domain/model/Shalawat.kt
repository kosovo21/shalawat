package com.example.shalawat.domain.model

/**
 * Clean domain model for a Shalawat entry.
 * Free of any framework annotations (Room, Hilt, etc).
 */
data class Shalawat(
    val id: Int = 0,
    val title: String = "",
    val arabicText: String = "",
    val transliteration: String = "",
    val translation: String = "",
    val audioFileName: String = "",
    val audioSource: String = "", // "asset" or "internal"
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
