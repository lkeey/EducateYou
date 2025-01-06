package dev.lkeeeey.edu.you.profile.presentation.students.viewmodel

sealed interface StudentsAction {
    data object OnOpenStudentDetails : StudentsAction
}