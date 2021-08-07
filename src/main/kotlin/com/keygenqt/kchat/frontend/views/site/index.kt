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

package com.keygenqt.kchat.frontend.views.site

import com.keygenqt.kchat.frontend.views.layouts.mainLayout
import kotlinx.html.*


fun HTML.index() {
    mainLayout {
        unsafe {
            raw("<style>.index-header-body {  display: inline-block; }</style>")
        }
        div(classes = "about-page") {
            div(classes = "round-top")
            div(classes = "about-page-data") {
                div(classes = "row") {
                    div(classes = "cell") {
                        div(classes = "phone") {
                            img(src = "images/site/phone.png")
                        }
                    }
                    div(classes = "cell") {
                        div(classes = "title") {
                            span {
                                +"K"
                            }
                            +"Chat"
                        }
                        div(classes = "text") {
                            h3 {
                                +"Platform Architecture"
                            }
                            h4 {
                                +"Front-End"
                            }
                            ul {
                                li {
                                    +"Framework - "
                                    a {
                                        target = "_blank"
                                        href = "https://ktor.io/"
                                        +"Ktor"
                                    }
                                }
                                li {
                                    +"Template - "
                                    a {
                                        target = "_blank"
                                        href = "https://github.com/Kotlin/kotlinx.html"
                                        +"kotlinx.html"
                                    }
                                }
                            }
                            h4 {
                                +"Back-End"
                            }
                            ul {
                                li {
                                    +"Framework - "
                                    a {
                                        target = "_blank"
                                        href = "https://ktor.io/"
                                        +"Ktor"
                                    }
                                }
                                li {
                                    +"Protocol -"
                                    a {
                                        target = "_blank"
                                        href = "https://en.wikipedia.org/wiki/WebSocket"
                                        +"WebSocket (chat)"
                                    }
                                    +" , "
                                    a {
                                        target = "_blank"
                                        href = "https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol"
                                        +"HTTP (REST API)"
                                    }
                                }
                                li {
                                    +"Data Base - "
                                    a {
                                        target = "_blank"
                                        href = "https://www.mysql.com/"
                                        +"MySQL"
                                    }
                                }
                            }
                            h4 {
                                +"Android client"
                            }
                            ul {
                                li {
                                    +"MVVM - "
                                    a {
                                        target = "_blank"
                                        href = "https://developer.android.com/jetpack/guide"
                                        +"Guide to app architecture"
                                    }
                                }
                                li {
                                    +"Gradle - "
                                    a {
                                        target = "_blank"
                                        href = "https://docs.gradle.org/current/userguide/kotlin_dsl.html"
                                        +"Kotlin DSL"
                                    }
                                }
                                li {
                                    +"Toolkit - "
                                    a {
                                        target = "_blank"
                                        href = "https://developer.android.com/jetpack/compose"
                                        +"Jetpack Compose"
                                    }
                                }
                                li {
                                    +"Target Sdk - "
                                    a {
                                        target = "_blank"
                                        href = "https://developer.android.com/about/versions/12"
                                        +"Android 12"
                                    }
                                }
                                li {
                                    +"Processing API - "
                                    a {
                                        target = "_blank"
                                        href = "https://github.com/google/ksp"
                                        +"KSP"
                                    }
                                }
                                li {
                                    +"HTTP client - "
                                    a {
                                        target = "_blank"
                                        href = "https://ktor.io/"
                                        +"Ktor"
                                    }
                                }
                            }
                        }
                    }
                }
            }
            div(classes = "round-bottom")
        }
    }
}