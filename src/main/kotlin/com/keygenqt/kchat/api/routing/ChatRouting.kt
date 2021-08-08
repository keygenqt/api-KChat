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

package com.keygenqt.kchat.api.routing

import com.keygenqt.kchat.common.base.ResponseError
import com.keygenqt.kchat.common.db.models.NewChat
import com.keygenqt.kchat.common.service.ChatService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.koin.ktor.ext.inject


fun Route.chatRoute() {

    val service: ChatService by inject()

    route("/chats") {

        get {
            val limit = call.parameters["limit"]?.toIntOrNull() ?: 5
            val offset = call.parameters["offset"]?.toIntOrNull() ?: 0
            call.respond(service.getAllChats(limit, offset))
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
            val chat = service.getChat(id)
            if (chat == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(chat)
        }

        post {
            val chat = call.receive<NewChat>()
            try {
                call.respond(HttpStatusCode.Created, service.addChat(chat))
            } catch (ex: ExposedSQLException) {
                call.respond(
                    status = HttpStatusCode.UnprocessableEntity,
                    message = ResponseError(ex.message ?: "Unprocessable Entity")
                )
            }
        }

        put {
            val chat = call.receive<NewChat>()
            val updated = service.updateChat(chat)
            if (updated == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(HttpStatusCode.OK, updated)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
            val removed = service.deleteChat(id)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

    }
}