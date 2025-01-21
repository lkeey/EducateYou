package dev.lkeeeey.edu.you.profile.domain

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.profile.domain.models.CreateTaskModel
import dev.lkeeeey.edu.you.profile.domain.models.ProfileModel
import dev.lkeeeey.edu.you.profile.domain.models.StudentModel
import dev.lkeeeey.edu.you.profile.domain.models.SubjectPresModel
import dev.lkeeeey.edu.you.profile.domain.models.UpdateBioModel

interface ProfileRepository {

    suspend fun getStudents() : Result<List<StudentModel>, DataError.Remote>

    suspend fun getStudentSubjects(
        studentUsername: String
    ) : Result<List<SubjectPresModel>, DataError.Remote>

    suspend fun getTeacherProfile() : Result<ProfileModel, DataError.Remote>
    suspend fun updateTeacherBio(
        bio: UpdateBioModel
    ) : Result<Unit, DataError.Remote>

    suspend fun createDistributedTask(
        task: CreateTaskModel,
        username: String,
    ): Result<CreateTaskModel, DataError.Remote>
}
