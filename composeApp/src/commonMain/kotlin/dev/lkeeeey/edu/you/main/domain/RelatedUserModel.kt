package dev.lkeeeey.edu.you.main.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelatedUserModel (
    @SerialName("username") val username : String = "",
    @SerialName("name") val name : String = "",
    @SerialName("avatar_url") val logo : String = "",
)