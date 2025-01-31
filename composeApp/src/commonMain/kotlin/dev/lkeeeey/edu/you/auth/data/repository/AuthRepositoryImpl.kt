package dev.lkeeeey.edu.you.auth.data.repository

import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.you.auth.domain.models.AuthResponse
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.you.auth.data.network.RemoteAuthDataSource
import dev.lkeeeey.edu.you.auth.domain.AuthRepository
import dev.lkeeeey.edu.you.auth.domain.models.LoginRequest

class AuthRepositoryImpl (
    private val remoteAuthDataSource: RemoteAuthDataSource,
) : AuthRepository {

    private val settings = Settings()

    override suspend fun loginUser(
        query: LoginRequest
    ): Result<AuthResponse, DataError.Remote> {
        return remoteAuthDataSource
            .loginUser(
                saveCookies = { refresh->
                    updateRefreshToken(
                        refresh = refresh
                    )
                },
                query = query
            )
    }

    override suspend fun registerUser(query: RegisterRequest): Result<Unit, DataError.Remote> {
        return remoteAuthDataSource
            .registerUser(query)
    }

    override suspend fun refreshToken(): Result<AuthResponse, DataError.Remote> {
        return remoteAuthDataSource.loginUser(
            query = LoginRequest(
                username = settings.getString(
                    key = Keys.LOGIN,
                    defaultValue = ""
                ),
                password = settings.getString(
                    key = Keys.PASSWORD,
                    defaultValue = ""
                ),
            ),
            saveCookies = {
                updateRefreshToken(
                    refresh = it
                )
            }
        )
    }

    override fun updateRefreshToken(refresh: String): Result<Unit, DataError.Local> {
        return try {
            settings.putString(
                key = Keys.REFRESH_TOKEN,
                value = refresh
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun updateAccessToken(access: String): Result<Unit, DataError.Local> {
        return try {
            settings.putString(
                key = Keys.ACCESS_TOKEN,
                value = access
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun updateAuthenticated(isAuthenticated: Boolean): Result<Unit, DataError.Local> {
        return try {
            settings.putBoolean(
                key = Keys.IS_LOGIN,
                value = isAuthenticated
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

}
