package dev.lkeeeey.edu.you.profile.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentModel (
    @SerialName("username") val name: String
)