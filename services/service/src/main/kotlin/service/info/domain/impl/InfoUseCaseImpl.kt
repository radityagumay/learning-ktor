package service.info.domain.impl

import io.ktor.http.Headers
import service.info.data.InfoRepository
import service.info.domain.InfoUseCase
import service.info.domain.model.InfoResponse
import service.info.domain.model.InfoResponse.Data

class InfoUseCaseImpl (
    private val repository: InfoRepository
): InfoUseCase {

    override suspend fun findBy(email: String, headers: Headers): InfoResponse {
        val entity = repository.findBy(email)
        val token = headers["token"] ?: "empty token"
        return InfoResponse(
            "success",
            200,
            Data(
                entity.email,
                entity.avatar,
                token
            )
        )
    }
}