package dev.lkeeeey.edu.you.profile.data.network

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.profile.domain.models.ProfileModel
import dev.lkeeeey.edu.you.profile.domain.models.StudentModel

interface ProfileDataSource {
    suspend fun getStudents(
        access: String
    ) : Result<List<StudentModel>, DataError.Remote>

    suspend fun getTeacherProfile(
        access: String,
        username: String
    ) : Result<ProfileModel, DataError.Remote>
}