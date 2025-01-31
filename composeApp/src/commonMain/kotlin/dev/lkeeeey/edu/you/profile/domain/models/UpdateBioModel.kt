package dev.lkeeeey.edu.you.profile.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateBioModel (
    @SerialName("text") val text : String
)