package dev.lkeeeey.edu.you.profile.presentation.students.viewmodel

import dev.lkeeeey.edu.you.profile.domain.models.StudentModel
import dev.lkeeeey.edu.you.profile.domain.models.SubjectPresModel
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.utils.MIN

data class StudentsState (
    val isLoading : Boolean = true,
    val students: List<StudentModel> = emptyList(),
    val errorMessage: String = "",

    val loadedSubjectsPres: List<SubjectPresModel> = emptyList(),

    val enteredContent: String = "",
    val enteredDeadline: LocalDate = LocalDate.MIN(),
    val enteredTime: Int = 0,
    val enteredSubject: SubjectPresModel = SubjectPresModel(-1, "", false),
    val chosenStudentUsername: String = "",
)