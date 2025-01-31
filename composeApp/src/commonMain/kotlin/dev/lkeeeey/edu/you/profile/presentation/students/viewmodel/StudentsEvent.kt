package dev.lkeeeey.edu.you.profile.presentation.students.viewmodel

import dev.lkeeeey.edu.you.profile.domain.models.SubjectPresModel
import kotlinx.datetime.LocalDate

sealed interface StudentsEvent {
    data class OnStudentClick(val username: String) : StudentsEvent
    data class OnEnterContent(val content: String) : StudentsEvent
    data class OnEnterSubject(val subject: SubjectPresModel) : StudentsEvent
    data class OnEnterExecutionTime(val time: Int) : StudentsEvent
    data class OnEnterDeadline(val deadline: LocalDate) : StudentsEvent
    data object OnLoadStudents : StudentsEvent
    data object OnSave : StudentsEvent
}
