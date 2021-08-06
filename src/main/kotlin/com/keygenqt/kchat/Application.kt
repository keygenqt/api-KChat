package com.keygenqt.kchat

import com.keygenqt.kchat.api.web.backend
import com.keygenqt.kchat.chat.web.chat
import com.keygenqt.kchat.frontend.config.routing.frontend
import com.keygenqt.kchat.frontend.views.site.error404
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.websocket.*

fun Application.module() {

    install(DefaultHeaders)
    install(WebSockets)

    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respondHtml { error404() }
        }
    }
    install(Routing) {
        frontend()
        backend()
        chat()
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}