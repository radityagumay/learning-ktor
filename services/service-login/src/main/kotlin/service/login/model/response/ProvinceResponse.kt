package service.login.model.response

import service.login.model.dto.ProvinceDto

data class ProvinceResponse(
    val id: String,
    val name: String
) {
    constructor(dto: ProvinceDto) : this(
        dto.id, dto.name
    )
}
