package main.kotlin.module.setup

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging

fun Application.loggingModule() {
    install(CallLogging)
}