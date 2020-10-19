package service.login.domain.model

import io.ktor.auth.Principal

data class LoginRequest(
    val email: String,
    val password: String
) : Principal