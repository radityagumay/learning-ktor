package extension

import io.ktor.application.ApplicationCall
import io.ktor.response.respond
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T : Any> ApplicationCall.responseAsync(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> T
) {
    val result: T = withContext(dispatcher) {
        block()
    }
    respond(result)
}