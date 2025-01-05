package dev.lkeeeey.edu.you.auth.data.network

import dev.lkeeeey.edu.auth.domain.models.AuthResponse
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.auth.domain.models.LoginRequest

interface RemoteAuthDataSource {

    suspend fun loginUser(
        query: LoginRequest,
        saveCookies: (String) -> Unit
    ): Result<AuthResponse, DataError.Remote>

    suspend fun refreshToken(
        saveCookies: (String) -> Unit
    ): Result<AuthResponse, DataError.Remote>


    suspend fun registerUser(
        query: RegisterRequest
    ): Result<Unit, DataError.Remote>

}
