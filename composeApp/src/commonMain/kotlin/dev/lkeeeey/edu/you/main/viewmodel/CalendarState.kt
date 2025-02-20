package dev.lkeeeey.edu.you.main.viewmodel

import dev.lkeeeey.edu.you.core.domain.getWeek
import dev.lkeeeey.edu.you.main.domain.LessonModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class CalendarState(
    val lessons: List<LessonModel> = emptyList(),
    val loadedDates: List<LocalDate> =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.getWeek(),

    val currentDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val selectedDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,

    val isLoading : Boolean = false,
    val error : String = ""
)
