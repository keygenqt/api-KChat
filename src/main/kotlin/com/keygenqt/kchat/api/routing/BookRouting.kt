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

import com.keygenqt.kchat.common.db.models.NewBook
import com.keygenqt.kchat.common.service.BookService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject


fun Route.bookRoute() {

    val bookService: BookService by inject()

    route("/book") {

        get {
            call.respond(bookService.getAllBooks())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
            val book = bookService.getBook(id)
            if (book == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(book)
        }

        post {
            val book = call.receive<NewBook>()
            call.respond(HttpStatusCode.Created, bookService.addBook(book))
        }

        put {
            val book = call.receive<NewBook>()
            val updated = bookService.updateBook(book)
            if (updated == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(HttpStatusCode.OK, updated)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
            val removed = bookService.deleteBook(id)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

    }
}