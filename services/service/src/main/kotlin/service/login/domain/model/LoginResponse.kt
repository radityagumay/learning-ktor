package service.login.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponse(
    @JsonProperty("message") val message: String,
    @JsonProperty("code") val code: Int,
    @JsonProperty("data") val data: Data
) {

    data class Data(
        @JsonProperty("user") val user: User,
        @JsonProperty("authorization") val authorization: Auth
    ) {

        data class User(
            @JsonProperty("email") val email: String
        )

        data class Auth(
            @JsonProperty("access_token") val accessToken: String,
            @JsonProperty("token_type") val tokenType: String,
            @JsonProperty("expires_in") val expiresIn: Int,
            @JsonProperty("scope") val scope: String,
            @JsonProperty("refresh_token") val refreshToken: String
        )
    }
}