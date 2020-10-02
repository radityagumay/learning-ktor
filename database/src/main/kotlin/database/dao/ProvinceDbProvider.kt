package database.dao

import org.jetbrains.exposed.sql.Database

object ProvinceDbProvider {
    fun execute() {
        ProvinceDaoImpl(
            Database.connect(
                url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                driver = "org.h2.Driver"
            )
        ).init()
    }
}