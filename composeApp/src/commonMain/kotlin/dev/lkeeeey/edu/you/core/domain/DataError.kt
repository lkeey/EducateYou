package dev.lkeeeey.edu.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNATHORIZED,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL,
        NO_USER,
        UNKNOWN
    }
}