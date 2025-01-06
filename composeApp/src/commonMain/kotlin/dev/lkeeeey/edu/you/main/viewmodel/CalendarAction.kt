package dev.lkeeeey.edu.you.main.viewmodel

sealed interface CalendarAction {
    data object OnOpenProfile : CalendarAction
}