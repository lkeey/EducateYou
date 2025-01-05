package dev.lkeeeey.edu.you.auth.data.network

import dev.lkeeeey.edu.you.auth.domain.models.AuthResponse
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.data.safeCallWithCookies
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.auth.domain.models.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody

private const val BASE_URL = "https://me-educate.ru/api"

class AuthDataSource(
    private val httpClient: HttpClient
): RemoteAuthDataSource {

    override suspend fun loginUser(
        query: LoginRequest,
        saveCookies: (String) -> Unit
    ): Result<AuthResponse, DataError.Remote> {
        return safeCallWithCookies<AuthResponse> (
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

    override suspend fun refreshToken(refresh: String , saveCookies: (String) -> Unit): Result<AuthResponse, DataError.Remote> {
        return safeCallWithCookies<AuthResponse> (
            saveToLocalDB = saveCookies
        ) {
            httpClient.post(
                urlString = "$BASE_URL/auth/refresh"
            ) {
                bearerAuth(
                    refresh
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
