package service.login.controller

import service.login.domain.LoginUseCase
import service.login.model.response.ProvinceResponse

class LoginController(
    private val useCase: LoginUseCase
) {
    suspend fun findBy(provinceId: String): ProvinceResponse {
        return useCase.findBy(provinceId).let(::ProvinceResponse)
    }

    suspend fun findAll(): List<ProvinceResponse> {
        return useCase.findAll().map(::ProvinceResponse)
    }
}

