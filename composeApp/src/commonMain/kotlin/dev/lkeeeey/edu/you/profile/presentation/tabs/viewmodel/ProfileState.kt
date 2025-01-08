package dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel

import dev.lkeeeey.edu.you.profile.domain.models.ProfileModel

data class ProfileState (
    val isLoading : Boolean = false,
    val errorMessage : String = "",
    val profile : ProfileModel = ProfileModel("Loading", "Loading", "Loading", "Loading")
)
