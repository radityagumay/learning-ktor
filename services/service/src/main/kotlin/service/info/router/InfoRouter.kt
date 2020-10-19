package service.info.router

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.readAllParts
import io.ktor.http.content.streamProvider
import io.ktor.locations.Location
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import org.koin.ktor.ext.inject
import service.info.controller.InfoController
import java.io.File
import java.io.InputStream
import java.io.OutputStream

fun Application.infoRouterModule() {
    val app = this
    routing {
        root(app)
    }
}

private fun Routing.root(app: Application) {
    val controller: InfoController by inject()

    get("/info/{email}") {
        val email = call.parameters["email"]!!
        val headers = call.request.headers
        call.respond(controller.findBy(email, headers))
    }

    post("/upload") { _ ->
        val multipart = call.receiveMultipart()
        val formItems = multipart.readAllParts()
        formItems.forEach { part ->
            if (part is PartData.FormItem) {
                println("FromItem : ${part.value}")
            } else if (part is PartData.FileItem) {
                val name = part.originalFileName!!
                val file = File(name)
                part.streamProvider().use { input ->
                    file.outputStream().buffered()
                        .use { output ->
                            input.copyToSuspend(output)
                        }
                }
                println("File is created with name : $name")
            }
            part.dispose
        }
    }

    get("/download/{name}") {
        val filename = call.parameters["name"]!!
        val file = File("/uploads/$filename")
        if (file.exists()) {
            call.respondFile(file)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

suspend fun InputStream.copyToSuspend(
    out: OutputStream,
    bufferSize: Int = DEFAULT_BUFFER_SIZE,
    yieldSize: Int = 4 * 1024 * 1024,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): Long {
    return withContext(dispatcher) {
        val buffer = ByteArray(bufferSize)
        var bytesCopied = 0L
        var bytesAfterYield = 0L
        while (true) {
            val bytes = read(buffer).takeIf { it >= 0 } ?: break
            out.write(buffer, 0, bytes)
            if (bytesAfterYield >= yieldSize) {
                yield()
                bytesAfterYield %= yieldSize
            }
            bytesCopied += bytes
            bytesAfterYield += bytes
        }
        return@withContext bytesCopied
    }
}