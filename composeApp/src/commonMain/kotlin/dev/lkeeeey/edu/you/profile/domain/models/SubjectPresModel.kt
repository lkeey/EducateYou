package dev.lkeeeey.edu.you.profile.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubjectPresModel(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("priority") val priority: Boolean,
)
