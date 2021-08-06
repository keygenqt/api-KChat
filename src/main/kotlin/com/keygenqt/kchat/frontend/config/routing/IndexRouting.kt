package com.keygenqt.kchat.frontend.config.routing

import com.keygenqt.kchat.frontend.views.site.index
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*

fun Route.index() {
    get("/") {
        call.respondHtml { index() }
    }
}
