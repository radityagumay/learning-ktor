package memory.cache

import memory.cache.internal.MemoryCacheImpl
import java.util.concurrent.ConcurrentHashMap

const val CACHE_USER_DATA = "in-memory"
const val CACHE_AUTH_DATA = "lookup"

interface MemoryCache {

    fun <T> getOrNull(key: String): T?
    fun putOrUpdate(key: String, value: Any)

    companion object {

        private val memoize = ConcurrentHashMap<String, MemoryCache>()

        fun instance(uniqueId: String): MemoryCache {
            if (memoize.containsKey(uniqueId).not()) {
                synchronized(this) {
                    val cache = MemoryCacheImpl()
                    memoize[uniqueId] = cache
                    return cache
                }
            }
            return memoize[uniqueId]!!
        }
    }
}



