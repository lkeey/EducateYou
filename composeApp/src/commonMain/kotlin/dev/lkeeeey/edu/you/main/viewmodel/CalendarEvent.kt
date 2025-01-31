package dev.lkeeeey.edu.you.main.viewmodel

import kotlinx.datetime.LocalDate

sealed interface CalendarEvent {
    data class OnDayClick(val date: LocalDate) : CalendarEvent
}
