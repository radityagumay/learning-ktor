package database.dao

import org.jetbrains.exposed.sql.ResultRow

data class Province(
    val id: Int,
    val name: String,
    val coordinate: String
) {
    constructor(row: ResultRow) : this(
        row[ProvinceTable.id],
        row[ProvinceTable.name],
        row[ProvinceTable.coordinate]
    )
}