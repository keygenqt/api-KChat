package com.keygenqt.kchat.common.util

import kotlinx.serialization.json.Json

object JsonMapper {
    val defaultMapper = Json {
        prettyPrint = true
    }
}