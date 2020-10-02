package service.login.domain

import service.login.model.dto.ProvinceDto

interface LoginUseCase {
    suspend fun findBy(provinceId: String): ProvinceDto
    suspend fun findAll(): List<ProvinceDto>
}

