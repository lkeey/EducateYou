package dev.lkeeeey.edu.you.profile.presentation.students.viewmodel

sealed interface StudentsEvent {
    data object OnStudentClick : StudentsEvent
    data object OnLoadStudents : StudentsEvent
}
