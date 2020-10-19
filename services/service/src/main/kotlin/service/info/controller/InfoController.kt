package service.info.controller

import io.ktor.http.Headers
import service.info.domain.InfoUseCase
import service.info.domain.model.InfoResponse

class InfoController(
    private val usecase: InfoUseCase
) {

    suspend fun findBy(email: String, headers: Headers): InfoResponse {
        return usecase.findBy(email, headers)
    }
}