package service.login.router

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.routing
import org.koin.ktor.ext.inject
import service.login.controller.LoginController
import service.login.domain.model.LoginRequest

fun Application.loginRouterModule() {
    val app = this
    routing {
        root(app)
    }
}

private fun Routing.root(app: Application) {
    val controller: LoginController by inject()

    post("/login") {
        val request = call.receive<LoginRequest>()
        println("RadityaLabs Backend Login request : $request")
        val response = controller.execute(request)
        println("RadityaLabs Backend Login response : $response")
        call.respond(response)
    }
}