package dev.lkeeeey.edu.you.profile.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBlockModel (
    @SerialName("subject") val subject: String = "",
    @SerialName("title") val title: String = "",
    @SerialName("tasks") val tasks: String,
    @SerialName("execution_time") val executionTime: Int,
)
