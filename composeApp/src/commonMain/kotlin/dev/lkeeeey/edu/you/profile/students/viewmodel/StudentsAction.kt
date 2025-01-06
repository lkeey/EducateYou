package dev.lkeeeey.edu.you.profile.students.viewmodel

sealed interface StudentsAction {
    data object OnOpenStudentDetails : StudentsAction
}