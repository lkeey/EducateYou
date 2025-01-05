package dev.lkeeeey.edu.auth.data.network

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.you.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.data.safeCallWithCookies
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

private const val BASE_URL = "https://me-educate.ru/api"

class AuthDataSource(
    private val httpClient: HttpClient
): RemoteAuthDataSource {

    override suspend fun loginUser(
        query: LoginRequest,
        saveCookies: (String) -> Unit
    ): Result<AuthLoginDto, DataError.Remote> {
        return safeCallWithCookies<AuthLoginDto> (
            saveToLocalDB = saveCookies
        ) {
            httpClient.post(
                urlString = "$BASE_URL/auth/login"
            ) {
                setBody(
                    query
                )
            }
        }
    }

    override suspend fun registerUser(
        query: RegisterRequest
    ): Result<Unit, DataError.Remote> {
        return safeCall<Unit> {
            httpClient.post(
                urlString = "$BASE_URL/auth/register"
            ) {
                setBody(
                    query
                )
            }
        }

    }

}
