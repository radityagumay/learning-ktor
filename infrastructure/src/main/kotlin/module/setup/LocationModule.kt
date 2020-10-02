package main.kotlin.module.setup

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.locations.Locations

@JvmOverloads
fun Application.locationModule() {
    install(Locations)
}