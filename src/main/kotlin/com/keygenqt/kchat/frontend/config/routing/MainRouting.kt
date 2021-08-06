package com.keygenqt.kchat.frontend.config.routing

import io.ktor.http.content.*
import io.ktor.routing.*
import java.io.File

fun Route.frontend() {
    staticRootFolder = File("src/main/kotlin/com/keygenqt/kchat/frontend/web")
    // routing static
    static("css") {
        files("css")
    }
    static("js") {
        files("js")
    }
    static("images") {
        files("images")
    }
    // routing pages
    index()
}