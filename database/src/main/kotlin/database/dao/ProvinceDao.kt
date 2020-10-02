package database.dao

import java.io.Closeable

interface ProvinceDao : Closeable {
    fun init()
    fun create(item: Province)
    fun update(item: Province)
    fun delete(id: Int)
    fun get(id: Int): Province?
    fun getAll(): List<Province>
}