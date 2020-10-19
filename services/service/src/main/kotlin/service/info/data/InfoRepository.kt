package service.info.data

import service.info.data.model.InfoEntity

interface InfoRepository {

    suspend fun findBy(email: String): InfoEntity
}