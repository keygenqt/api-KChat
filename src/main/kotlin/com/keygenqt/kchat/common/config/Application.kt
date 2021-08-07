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

package com.keygenqt.kchat.common.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.keygenqt.kchat.api.routing.apiRoute
import com.keygenqt.kchat.chat.routing.chatRoute
import com.keygenqt.kchat.common.di.moduleServicesDI
import com.keygenqt.kchat.common.util.Constants.DBCONFIG_CONFIG_KEY
import com.keygenqt.kchat.common.util.JsonMapper
import com.keygenqt.kchat.frontend.routing.frontendRoute
import com.keygenqt.kchat.frontend.views.site.error404
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.websocket.*
import org.koin.core.context.startKoin
import java.io.FileInputStream


fun Application.module() {

    // firebase
    val serviceAccount = FileInputStream("keys/kchat-b249c-firebase-adminsdk-nit6n-9ca11b0d0a.json")

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()

    FirebaseApp.initializeApp(options)

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
            call.respondHtml(status = HttpStatusCode.NotFound) { error404() }
        }
    }
    install(Authentication) {
        basic("firebase-token") {
            realm = "Access FirebaseAuth"
            validate { credentials ->
                try {
                    FirebaseAuth.getInstance().verifyIdToken(credentials.name)?.let {
                        UserIdPrincipal(it.uid)
                    }
                } catch (ex: Exception) {
                    null
                }
            }
        }
    }
    install(Routing) {
        authenticate("firebase-token") {
            apiRoute()
        }
        frontendRoute()
        chatRoute()
    }
}