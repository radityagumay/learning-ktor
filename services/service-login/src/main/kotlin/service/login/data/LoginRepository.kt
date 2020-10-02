package service.login.data

import service.login.model.dto.ProvinceDto

interface LoginRepository {
    suspend fun findAll(): List<ProvinceDto>
    suspend fun findBy(provinceId: String): ProvinceDto?
}
