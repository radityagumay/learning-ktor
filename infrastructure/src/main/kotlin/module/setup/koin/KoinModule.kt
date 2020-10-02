package main.kotlin.module.setup.koin

import io.ktor.application.Application
import io.ktor.application.install
import main.kotlin.module.setup.koin.deps.provinceKoinModule
import org.koin.ktor.ext.Koin

fun Application.koinModule() {
    install(Koin) {
        modules(provinceKoinModule)
    }
}
