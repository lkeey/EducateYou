package dev.lkeeeey.edu.you.profile.data.repository

import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.main.domain.LessonModel
import dev.lkeeeey.edu.you.profile.data.network.ProfileDataSource
import dev.lkeeeey.edu.you.profile.domain.ProfileRepository
import dev.lkeeeey.edu.you.profile.domain.models.CreateBlockModel
import dev.lkeeeey.edu.you.profile.domain.models.CreateTaskModel
import dev.lkeeeey.edu.you.profile.domain.models.ProfileModel
import dev.lkeeeey.edu.you.profile.domain.models.StudentModel
import dev.lkeeeey.edu.you.profile.domain.models.SubjectPresModel
import dev.lkeeeey.edu.you.profile.domain.models.UpdateBioModel

class ProfileRepositoryImpl(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {

    private val settings = Settings()

    override suspend fun getStudents(): Result<List<StudentModel>, DataError.Remote> {
        return profileDataSource.getStudents(
            access = getAccess()
        )
    }

    override suspend fun getStudentSubjects(studentUsername: String): Result<List<SubjectPresModel>, DataError.Remote> {
        return profileDataSource.getStudentSubjects(
            access = getAccess(),
            username = studentUsername
        )
    }

    override suspend fun getTeacherProfile(): Result<ProfileModel, DataError.Remote> {
        return profileDataSource.getTeacherProfile(
            access = getAccess(),
            username = getUsername()
        )
    }

    override suspend fun updateTeacherBio(bio: UpdateBioModel): Result<Unit, DataError.Remote> {
        return profileDataSource.updateTeacherBio(
            access = getAccess(),
            bio = bio
        )
    }

    override suspend fun createDistributedTask(task: CreateTaskModel, username: String): Result<CreateTaskModel, DataError.Remote> {
        return profileDataSource.createDistributedTask(
            username = username,
            access = getAccess(),
            task = task
        )
    }

    override suspend fun createBlockOfTasks(block: CreateBlockModel): Result<CreateBlockModel, DataError.Remote> {
        return profileDataSource.createBlockOfTasks(
            access = getAccess(),
            block = block
        )
    }

    override suspend fun getTimetable(): Result<List<LessonModel>, DataError.Remote> {
        return profileDataSource.getTimetable(
            access = getAccess()
        )
    }

    private fun getAccess() : String {
        return settings.getString(
            key = Keys.ACCESS_TOKEN,
            defaultValue = ""
        )
    }

    private fun getUsername() : String {
        return settings.getString(
            key = Keys.MY_USERNAME,
            defaultValue = ""
        )
    }

}
