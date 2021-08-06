package com.keygenqt.kchat.frontend.views.site

import com.keygenqt.kchat.frontend.views.layouts.mainLayout
import kotlinx.html.*

fun HTML.error404() {
    mainLayout {
        div(classes = "container-body error-page") {
            div(classes = "error-page-cell") {
                div(classes = "error mx-auto") {
                    +"404"
                }
                p(classes = "lead text-gray-800 mb-5") {
                    +"Page Not Found"
                }
                p(classes = "text-gray-500") {
                    +"It looks like you found a glitch in the matrix..."
                }
                a(href = "/") {
                    style = "padding-bottom: 5px"
                    span { +"←" }
                }
            }
        }
    }
}