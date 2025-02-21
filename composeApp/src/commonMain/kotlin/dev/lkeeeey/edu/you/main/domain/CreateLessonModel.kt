package dev.lkeeeey.edu.you.main.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateLessonModel (
    @SerialName("weekday") val weekday : Int = 0,
    @SerialName("start") val start : String = "",
    @SerialName("end") val end : String = "",
    @SerialName("student_username") val student : String = "",
)
