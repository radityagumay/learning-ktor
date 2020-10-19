package service.info.data.impl

import memory.cache.MemoryCache
import service.info.data.InfoRepository
import service.info.data.model.InfoEntity
import service.login.data.model.AuthEntity
import java.net.HttpURLConnection
import javax.xml.ws.http.HTTPException

class InfoRepositoryImpl(
    private val authDao: MemoryCache
) : InfoRepository {

    override suspend fun findBy(email: String): InfoEntity {
        return authDao.getOrNull<AuthEntity>(email)?.let {
            InfoEntity(
                email,
                "https://www.gstatic.com/tv/thumb/persons/614/614_v9_bc.jpg"
            )
        } ?: throw HTTPException(HttpURLConnection.HTTP_NOT_FOUND)
    }
}