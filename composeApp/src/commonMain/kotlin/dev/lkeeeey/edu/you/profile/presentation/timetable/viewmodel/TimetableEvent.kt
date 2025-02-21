package dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel

import dev.lkeeeey.edu.you.main.domain.CreateLessonModel
import dev.lkeeeey.edu.you.main.domain.LessonModel

sealed interface TimetableEvent {
    data class OnChangeDay(val index : Int): TimetableEvent
    data class OnUpdateLesson(
        val deletedLesson : Int,
        val newLesson: CreateLessonModel
    ): TimetableEvent

    data object OnLoadLessons: TimetableEvent
    data object OnAddLesson : TimetableEvent
    data object OnSaveDay : TimetableEvent
}