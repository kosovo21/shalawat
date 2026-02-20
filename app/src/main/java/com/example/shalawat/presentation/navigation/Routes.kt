package com.example.shalawat.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val id: Int)

@Serializable
data class AddEditRoute(val id: Int? = null)
