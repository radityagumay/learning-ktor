@file:Suppress("UNCHECKED_CAST")

package memory.cache.internal

import memory.cache.MemoryCache
import java.util.concurrent.ConcurrentHashMap

internal class MemoryCacheImpl : MemoryCache {

    private val map = ConcurrentHashMap<String, Any>()

    override fun <T> getOrNull(key: String): T? {
        return map[key] as T
    }

    override fun putOrUpdate(key: String, value: Any) {
        map[key] = value
    }
}