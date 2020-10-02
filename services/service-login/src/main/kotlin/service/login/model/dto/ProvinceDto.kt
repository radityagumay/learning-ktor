package service.login.model.dto

data class ProvinceDto(
    val id: String,
    val name: String,
    val coordinate: ProvinceCoordinate,
    val positive: Int,
    val death: Int,
    val recover: Int,
    val mark: Int
) {
    data class ProvinceCoordinate(
        val latitude: Double,
        val longitude: Double
    )
}