package dev.lkeeeey.edu.you.profile.data.network

import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.main.domain.LessonModel
import dev.lkeeeey.edu.you.profile.domain.models.CreateBlockModel
import dev.lkeeeey.edu.you.profile.domain.models.CreateTaskModel
import dev.lkeeeey.edu.you.profile.domain.models.ProfileModel
import dev.lkeeeey.edu.you.profile.domain.models.StudentModel
import dev.lkeeeey.edu.you.profile.domain.models.SubjectPresModel
import dev.lkeeeey.edu.you.profile.domain.models.UpdateBioModel
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody

private const val BASE_URL = "https://me-educate.ru/api"

class ProfileDataSourceImpl (
    private val httpClient: HttpClient
) : ProfileDataSource {

    override suspend fun getStudents(access: String): Result<List<StudentModel>, DataError.Remote> {
        return safeCall<List<StudentModel>> {
            httpClient.get(
                urlString = "$BASE_URL/schedule/student"
            ) {
                bearerAuth(
                    access
                )
            }
        }
    }

    override suspend fun getStudentSubjects(
        access: String,
        username: String
    ): Result<List<SubjectPresModel>, DataError.Remote> {
        return safeCall<List<SubjectPresModel>> {
            httpClient.get(
                urlString = "$BASE_URL/schedule/subject/$username"
            ) {
                bearerAuth(
                    access
                )
            }
        }
    }

    override suspend fun getTeacherProfile(
        access: String,
        username: String
    ): Result<ProfileModel, DataError.Remote> {
        return safeCall<ProfileModel> {
            httpClient.get(
                urlString = "$BASE_URL/auth/user/$username"
            ) {
                bearerAuth(
                    access
                )
            }
        }
    }

    override suspend fun updateTeacherBio(
        access: String,
        bio: UpdateBioModel
    ): Result<Unit, DataError.Remote> {
        return safeCall<Unit> {
            httpClient.patch(
                urlString = "$BASE_URL/auth/bio"
            ) {
                setBody(
                    bio
                )
                bearerAuth(
                    access
                )
            }
        }
    }

    override suspend fun createDistributedTask(
        access: String,
        username: String,
        task: CreateTaskModel
    ): Result<CreateTaskModel, DataError.Remote> {
        return safeCall<CreateTaskModel> {
            httpClient.post(
                urlString = "$BASE_URL/schedule/homework/$username"
            ) {
                bearerAuth(
                    access
                )
                setBody(
                    task
                )
            }
        }
    }

    override suspend fun createBlockOfTasks(
        access: String,
        block: CreateBlockModel
    ): Result<CreateBlockModel, DataError.Remote> {
        return safeCall<CreateBlockModel> {
            httpClient.post(
                urlString = "$BASE_URL/task/block"
            ) {
                bearerAuth(
                    access
                )
                setBody(
                    block
                )
            }
        }
    }

    override suspend fun getTimetable(access: String): Result<List<LessonModel>, DataError.Remote> {
        return safeCall<List<LessonModel>> {
            httpClient.get(
                urlString = "$BASE_URL/schedule"
            ) {
                bearerAuth(
                    access
                )
            }
        }
    }
}
