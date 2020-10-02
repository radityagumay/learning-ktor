package service.login.domain.impl

import service.login.data.LoginRepository
import service.login.domain.LoginUseCase
import service.login.model.dto.ProvinceDto

class LoginUseCaseImpl(
    private val repository: LoginRepository
) : LoginUseCase {
    override suspend fun findBy(provinceId: String): ProvinceDto {
        return repository.findBy(provinceId) ?: throw IllegalAccessException(
            "No Province Found for Given Id: $provinceId"
        )
    }

    override suspend fun findAll(): List<ProvinceDto> {
        return repository.findAll()
    }
}
