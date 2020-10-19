package service.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import service.login.domain.model.LoginRequest
import service.model.UserModel
import java.security.SecureRandom
import java.util.Date

fun Application.authenticationModule() {
    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = "RadityaLabs"
            validate {
                val name = it.payload.getClaim("email").asString()
                val password = it.payload.getClaim("password").asString()
                if (name != null && password != null) {
                    LoginRequest(name, password)
                } else {
                    null
                }
            }
        }
    }
}

object JwtConfig {

    // typically the secret will be read from CI variables
    // for now that's fine add here
    private const val secret = "my-secret"
    private const val issuer = "RadityaLabs"
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    /**
     * Produce a token for this combination of name and password
     */
    fun generateToken(user: UserModel, expiredInDays: Int): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("email", user.email)
        .withClaim("password", user.password)
        .withExpiresAt(expiredIn(expiredInDays))
        .sign(algorithm)

    /**
     * Calculate the expiration Date based on current time + the given validity
     */
    private fun expiredIn(days: Int) = Date(System.currentTimeMillis() + (36_000_00 * days))

    private fun generateSalt(): String {
        val random: SecureRandom = SecureRandom()
        val salt: ByteArray = ByteArray(16)
        random.nextBytes(salt)
        return salt.toString()
    }
}