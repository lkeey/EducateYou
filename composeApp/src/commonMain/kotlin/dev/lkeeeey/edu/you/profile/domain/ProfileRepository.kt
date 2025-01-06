package dev.lkeeeey.edu.you.profile.domain

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.profile.domain.models.StudentModel

interface ProfileRepository {

    suspend fun getStudents() : Result<List<StudentModel>, DataError.Remote>

}
