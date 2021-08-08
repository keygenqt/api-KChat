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

package com.keygenqt.kchat.common.service

import com.keygenqt.kchat.common.config.DBConfig.dbQuery
import com.keygenqt.kchat.common.db.ChangeType
import com.keygenqt.kchat.common.db.Notification
import com.keygenqt.kchat.common.db.models.Chat
import com.keygenqt.kchat.common.db.models.Chats
import com.keygenqt.kchat.common.db.models.NewChat
import org.jetbrains.exposed.sql.*

class ChatService {

    private val listeners = mutableMapOf<Int, suspend (Notification<Chat?>) -> Unit>()

    fun addChangeListener(id: Int, listener: suspend (Notification<Chat?>) -> Unit) {
        listeners[id] = listener
    }

    fun removeChangeListener(id: Int) = listeners.remove(id)

    private suspend fun onChange(type: ChangeType, id: Int, entity: Chat? = null) {
        listeners.values.forEach {
            it.invoke(Notification(type, id, entity))
        }
    }

    suspend fun getAllChats(limit: Int, offset: Int): List<Chat> = dbQuery {
        Chats.selectAll().limit(limit, offset.toLong()).map { toChat(it) }
    }

    suspend fun getChat(id: Int): Chat? = dbQuery {
        Chats.select {
            (Chats.id eq id)
        }.mapNotNull { toChat(it) }
            .singleOrNull()
    }

    suspend fun updateChat(Chat: NewChat): Chat? {
        val id = Chat.id
        return if (id == null) {
            addChat(Chat)
        } else {
            dbQuery {
                Chats.update({ Chats.id eq id }) {
                    it[userId] = Chat.userId
                    it[name] = Chat.name
                    it[dateUpdated] = System.currentTimeMillis()
                }
            }
            getChat(id).also {
                onChange(ChangeType.UPDATE, id, it)
            }
        }
    }

    suspend fun addChat(Chat: NewChat): Chat {
        var key = 0
        dbQuery {
            key = (Chats.insert {
                it[userId] = Chat.userId
                it[name] = Chat.name
                it[dateUpdated] = System.currentTimeMillis()
            } get Chats.id)
        }
        return getChat(key)!!.also {
            onChange(ChangeType.CREATE, key, it)
        }
    }

    suspend fun deleteChat(id: Int): Boolean {
        return dbQuery {
            Chats.deleteWhere { Chats.id eq id } > 0
        }.also {
            if (it) onChange(ChangeType.DELETE, id)
        }
    }

    private fun toChat(row: ResultRow): Chat =
        Chat(
            id = row[Chats.id],
            userId = row[Chats.userId],
            name = row[Chats.name],
            dateUpdated = row[Chats.dateUpdated]
        )
}