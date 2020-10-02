package main.kotlin.module.setup

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import mapper.ObjectMapperBuilder

@kotlin.jvm.JvmOverloads
fun Application.jacksonModule() {
    install(ContentNegotiation) {
        jackson {
            ObjectMapperBuilder.build(this)
            configure(SerializationFeature.INDENT_OUTPUT, true)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
    }
}