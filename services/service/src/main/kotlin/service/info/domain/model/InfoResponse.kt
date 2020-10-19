package service.info.domain.model

data class InfoResponse(
    val message: String,
    val code: Int,
    val data: Data
) {

    data class Data(
        val email: String,
        val avatar: String,
        val token: String
    )
}