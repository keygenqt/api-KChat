/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
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