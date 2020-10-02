package database.dao

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ProvinceDaoImpl(
    private val db: Database
) : ProvinceDao {
    override fun init() = transaction {
        SchemaUtils.create(ProvinceTable)
    }

    override fun create(item: Province) = transaction(db) {
        ProvinceTable.insert {
            it[name] = item.name
            it[coordinate] = item.coordinate
        }.let { Unit }
    }

    override fun update(item: Province) = transaction(db) {
        ProvinceTable.update({ ProvinceTable.id eq item.id }) {
            it[name] = item.name
            it[coordinate] = item.coordinate
        }.let { Unit }
    }

    override fun delete(id: Int) = transaction(db) {
        ProvinceTable
            .deleteWhere { ProvinceTable.id eq id }
            .let { Unit }
    }

    override fun get(id: Int): Province? = transaction(db) {
        ProvinceTable
            .select { ProvinceTable.id eq id }
            .map(::Province)
            .singleOrNull()
    }

    override fun getAll(): List<Province> = transaction(db) {
        ProvinceTable.selectAll().map(::Province)
    }

    override fun close() {}
}