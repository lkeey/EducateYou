package dev.lkeeeey.edu.you.main.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonModel (
    @SerialName("id") val id : Int = 0,
    @SerialName("type") val type : String = "",
    @SerialName("weekday") val weekday : Int = 0,
    @SerialName("start") val start : String = "",
    @SerialName("end") val end : String = "",
    @SerialName("related_user") val user : RelatedUserModel = RelatedUserModel(),
)