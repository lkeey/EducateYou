package dev.lkeeeey.edu.you.profile.presentation.students.viewmodel

sealed interface StudentsEvent {
    data class OnStudentClick(val username: String) : StudentsEvent
    data object OnLoadStudents : StudentsEvent
}
