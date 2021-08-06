package com.keygenqt.kchat.common.db

import com.keygenqt.kchat.common.db.models.Book

enum class ChangeType { CREATE, UPDATE, DELETE }

data class Notification<T>(val type: ChangeType, val id: Int, val entity: T)

typealias BookNotification = Notification<Book?>
