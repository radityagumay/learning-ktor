package main.kotlin.module.setup

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

@JvmOverloads
fun Application.statusPagesModule() {
    install(StatusPages) {
        exception<Throwable> { cause ->
            log.error(cause.message, cause)
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
}