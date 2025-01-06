package dev.lkeeeey.edu.you.profile.students.viewmodel

sealed interface StudentsEvent {
    data object OnStudentClick : StudentsEvent
}
