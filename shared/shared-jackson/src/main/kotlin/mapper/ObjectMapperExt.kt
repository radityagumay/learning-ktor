package mapper

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

val objectMapper by lazy { jacksonObjectMapper() }