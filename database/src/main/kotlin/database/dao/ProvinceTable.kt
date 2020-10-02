package database.dao

import org.jetbrains.exposed.sql.Table

object ProvinceTable : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("name", 50)
    val coordinate = varchar("coordinate", 1000)
}