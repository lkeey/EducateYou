package dev.lkeeeey.edu.you.profile.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBlockTaskModel (
    @SerialName("content") val content: String = "",
    @SerialName("answer") val answer: String = "",
)