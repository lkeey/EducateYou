package dev.lkeeeey.edu.you.profile.presentation.students.viewmodel

import dev.lkeeeey.edu.you.profile.domain.models.StudentModel

data class StudentsState (
    val isLoading : Boolean = true,
    val students: List<StudentModel> = emptyList(),
    val errorMessage: String = ""
)