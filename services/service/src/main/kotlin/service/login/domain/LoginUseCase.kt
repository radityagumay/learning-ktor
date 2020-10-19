package service.login.domain

import service.login.domain.model.LoginRequest
import service.login.domain.model.LoginResponse

interface LoginUseCase {

    suspend fun execute(request: LoginRequest): LoginResponse
}

