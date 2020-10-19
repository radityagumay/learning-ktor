package service.login.data.model

data class UserEntity(
    val email: String,
    val hashedPassword: String
)