package dev.lkeeeey.edu.you.profile.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileModel(
    @SerialName("name") val name : String,
    @SerialName("username") val username : String,
    @SerialName("bio") val bio : String,
    @SerialName("avatar") val logo : String,
)
