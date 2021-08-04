package com.keygenqt.kchat

import io.ktor.application.*
import io.ktor.server.netty.*
import io.ktor.websocket.*

@Suppress("unused")
fun Application.module() = install(WebSockets)

fun main(args: Array<String>): Unit = EngineMain.main(args)