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
 
package com.keygenqt.kchat.common.db.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Books : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 255)
    val author = varchar("author", 255)
    val dateUpdated = long("dateUpdated")
    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val dateUpdated: Long
)

@Serializable
data class NewBook(
    val id: Int?,
    val title: String,
    val author: String
)