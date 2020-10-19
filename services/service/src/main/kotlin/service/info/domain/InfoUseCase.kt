package service.info.domain

import io.ktor.http.Headers
import service.info.domain.model.InfoResponse

interface InfoUseCase {

    suspend fun findBy(email: String, headers: Headers): InfoResponse
}