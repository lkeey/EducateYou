package dev.lkeeeey.edu.you.auth.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse (
    @SerialName("access") val access: String
)
