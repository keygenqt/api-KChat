package com.keygenqt.kchat.common.config

import com.keygenqt.kchat.api.routing.apiRoute
import com.keygenqt.kchat.chat.routing.chatRoute
import com.keygenqt.kchat.common.di.moduleServicesDI
import com.keygenqt.kchat.common.util.Constants.DBCONFIG_CONFIG_KEY
import com.keygenqt.kchat.common.util.JsonMapper
import com.keygenqt.kchat.frontend.routing.frontendRoute
import com.keygenqt.kchat.frontend.views.site.error404
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.websocket.*
import org.koin.core.context.startKoin

fun Application.module() {

    // di
    startKoin {
        printLogger()
        modules(
            moduleServicesDI,
        )
    }

    // serialization
    install(ContentNegotiation) {
        json(JsonMapper.defaultMapper)
    }

    // db
    DBConfig.connectAndMigrate(environment.config.property(DBCONFIG_CONFIG_KEY).getString())

    // init ktor
    install(DefaultHeaders)
    install(WebSockets)
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respondHtml { error404() }
        }
    }
    install(Routing) {
        frontendRoute()
        chatRoute()
        apiRoute()
    }
}