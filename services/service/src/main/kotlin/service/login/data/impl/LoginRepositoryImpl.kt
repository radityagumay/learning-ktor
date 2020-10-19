package service.login.data.impl

import memory.cache.MemoryCache
import service.auth.JwtConfig
import service.login.data.LoginRepository
import service.login.data.model.AuthEntity
import service.login.data.model.LoginEntity
import service.login.data.model.UserEntity
import service.model.UserModel
import java.security.MessageDigest

class LoginRepositoryImpl(
    private val userDao: MemoryCache,
    private val authDao: MemoryCache
) : LoginRepository {

    override suspend fun execute(user: UserModel): LoginEntity {
        val hashPassword = convertToHash(user.password)
        val hashedUser = user.copy(password = hashPassword)

        val accessToken = JwtConfig.generateToken(hashedUser, 1)
        val refreshToken = JwtConfig.generateToken(hashedUser, 7)

        val userEntity = UserEntity(user.email, hashPassword)
        val authEntity = AuthEntity(accessToken, refreshToken)

        userDao.putOrUpdate(user.email, userEntity)
        authDao.putOrUpdate(user.email, authEntity)

        return LoginEntity(user.email, authEntity)
    }

    private fun convertToHash(plainText: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        messageDigest.update(plainText.toByteArray())
        return String(messageDigest.digest())
    }
}
