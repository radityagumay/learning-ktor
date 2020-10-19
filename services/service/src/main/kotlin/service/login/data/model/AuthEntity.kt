package service.login.data.model

data class AuthEntity(
    val accessToken: String,
    val refreshToken: String
)