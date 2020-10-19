package service.login.controller

import service.login.domain.LoginUseCase
import service.login.domain.model.LoginRequest
import service.login.domain.model.LoginResponse

class LoginController(
    private val useCase: LoginUseCase
) {

    suspend fun execute(request: LoginRequest): LoginResponse {
        return useCase.execute(request)
    }
}

