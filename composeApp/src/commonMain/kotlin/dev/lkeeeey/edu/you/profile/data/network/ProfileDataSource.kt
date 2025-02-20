package dev.lkeeeey.edu.you.profile.data.network

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.main.domain.LessonModel
import dev.lkeeeey.edu.you.profile.domain.models.CreateBlockModel
import dev.lkeeeey.edu.you.profile.domain.models.CreateTaskModel
import dev.lkeeeey.edu.you.profile.domain.models.ProfileModel
import dev.lkeeeey.edu.you.profile.domain.models.StudentModel
import dev.lkeeeey.edu.you.profile.domain.models.SubjectPresModel
import dev.lkeeeey.edu.you.profile.domain.models.UpdateBioModel

interface ProfileDataSource {
    suspend fun getStudents(
        access: String
    ) : Result<List<StudentModel>, DataError.Remote>

    suspend fun getStudentSubjects(
        access: String,
        username: String,
    ) : Result<List<SubjectPresModel>, DataError.Remote>

    suspend fun getTeacherProfile(
        access: String,
        username: String
    ) : Result<ProfileModel, DataError.Remote>

    suspend fun updateTeacherBio(
        access: String,
        bio: UpdateBioModel
    ) : Result<Unit, DataError.Remote>

    suspend fun createDistributedTask(
        access: String,
        username: String,
        task: CreateTaskModel
    ) : Result<CreateTaskModel, DataError.Remote>

    suspend fun createBlockOfTasks(
        access: String,
        block: CreateBlockModel,
    ): Result<CreateBlockModel, DataError.Remote>

    suspend fun getTimetable(
        access: String
    ): Result<List<LessonModel>, DataError.Remote>

}
