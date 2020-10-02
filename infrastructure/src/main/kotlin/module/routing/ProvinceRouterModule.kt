@file:Suppress("EXPERIMENTAL_API_USAGE")

package main.kotlin.module.routing

import extension.responseAsync
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import kotlinx.coroutines.Dispatchers
import org.koin.ktor.ext.inject
import service.login.controller.ProvinceController

fun Application.provinceRouterModule() {
    val app = this
    routing {
        root(app)
    }
}

@Location("/{id}")
data class ProvinceParam(val id: String)

private fun Routing.root(app: Application) {
    val controller: ProvinceController by inject()

    route("/province") {
        get<ProvinceParam> { param ->
            call.responseAsync(Dispatchers.IO) {
                controller.findBy(param.id)
            }
        }

        get {
            call.respond(controller.findAll())
        }
    }
}