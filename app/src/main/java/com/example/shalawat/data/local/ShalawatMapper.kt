package com.example.shalawat.data.local

import com.example.shalawat.domain.model.Shalawat

/**
 * Extension functions for mapping between [ShalawatEntity] and [Shalawat] domain model.
 */

fun ShalawatEntity.toDomain(): Shalawat {
    return Shalawat(
        id = id,
        title = title,
        arabicText = arabicText,
        transliteration = transliteration,
        translation = translation,
        audioFileName = audioFileName,
        audioSource = audioSource,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Shalawat.toEntity(): ShalawatEntity {
    return ShalawatEntity(
        id = id,
        title = title,
        arabicText = arabicText,
        transliteration = transliteration,
        translation = translation,
        audioFileName = audioFileName,
        audioSource = audioSource,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
