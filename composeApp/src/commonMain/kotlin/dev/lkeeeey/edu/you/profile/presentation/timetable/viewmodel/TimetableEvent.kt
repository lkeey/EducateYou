package dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel

sealed interface TimetableEvent {
    data class OnChangeDay(val index : Int): TimetableEvent

    data class OnDeleteLesson(
        val deletedLessonId : Int,
    ): TimetableEvent

    data object OnLoadLessons: TimetableEvent
    data object OnSaveLesson : TimetableEvent
}