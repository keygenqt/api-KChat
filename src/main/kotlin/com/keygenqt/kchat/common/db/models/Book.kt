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