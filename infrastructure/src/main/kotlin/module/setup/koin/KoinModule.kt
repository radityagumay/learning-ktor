package main.kotlin.module.setup.koin

import io.ktor.application.Application
import io.ktor.application.install
import org.koin.ktor.ext.Koin
import service.info.di.infoKoinModule
import service.login.di.loginKoinModule

fun Application.koinModule() {
    install(Koin) {
        modules(loginKoinModule, infoKoinModule)
    }
}
