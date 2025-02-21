package dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel

import dev.lkeeeey.edu.you.main.domain.CreateLessonModel
import dev.lkeeeey.edu.you.main.domain.LessonModel
import dev.lkeeeey.edu.you.profile.domain.models.StudentModel

data class TimetableState (
    val isLoading : Boolean = false,
    val error : String = "",
    val lessons : List<LessonModel> = emptyList(),
    val enteredLesson : CreateLessonModel = CreateLessonModel(),
    val students : List<StudentModel> = emptyList(),
    val weekDay : Int = 0
)