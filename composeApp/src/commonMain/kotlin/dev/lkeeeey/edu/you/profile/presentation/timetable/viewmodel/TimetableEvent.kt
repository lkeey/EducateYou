package dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel

sealed interface TimetableEvent {
    data class OnChangeDay(val index : Int): TimetableEvent

    data class OnDeleteLesson(
        val deletedLessonId : Int,
    ): TimetableEvent

    data class OnUpdateEnteredStudent(
        val student : String,
    ): TimetableEvent

    data class OnUpdateStart(
        val start : String,
    ): TimetableEvent

    data class OnUpdateEnd(
        val end : String,
    ): TimetableEvent

    data object OnSaveLesson : TimetableEvent

    data object OnLoadLessons: TimetableEvent
    data object OnLoadStudents: TimetableEvent

}