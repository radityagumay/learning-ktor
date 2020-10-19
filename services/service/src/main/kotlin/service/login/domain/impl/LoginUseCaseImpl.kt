package service.login.domain.impl

import service.login.data.LoginRepository
import service.login.domain.LoginUseCase
import service.login.domain.model.LoginRequest
import service.login.domain.model.LoginResponse
import service.login.domain.model.LoginResponse.Data
import service.login.domain.model.LoginResponse.Data.Auth
import service.login.domain.model.LoginResponse.Data.User
import service.model.UserModel
import javax.net.ssl.HttpsURLConnection

class LoginUseCaseImpl(
    private val repository: LoginRepository
) : LoginUseCase {

    override suspend fun execute(request: LoginRequest): LoginResponse {
        val user = UserModel(
            request.email,
            request.password
        )
        val result = repository.execute(user)
        return LoginResponse(
            "success",
            HttpsURLConnection.HTTP_CREATED,
            Data(
                User(result.email),
                Auth(
                    accessToken = result.authEntity.accessToken,
                    refreshToken = result.authEntity.refreshToken,
                    tokenType = "bearer",
                    scope = "create",
                    expiresIn = 3600
                )
            )
        )
    }
}
