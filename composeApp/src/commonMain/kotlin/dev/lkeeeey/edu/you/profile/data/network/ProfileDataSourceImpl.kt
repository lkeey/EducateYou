package dev.lkeeeey.edu.you.profile.data.network

import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.profile.domain.models.StudentModel
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
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

}
