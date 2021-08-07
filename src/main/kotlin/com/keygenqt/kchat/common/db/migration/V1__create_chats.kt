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
 
package com.keygenqt.kchat.common.db.migration

import com.keygenqt.kchat.common.db.models.Chats
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

@Suppress("unused")
class V1__create_chats : BaseJavaMigration() {
    override fun migrate(context: Context?) {
        transaction {
            SchemaUtils.create(Chats)

            // First chat Second chat Third chat Fourth chat Fifth chat

            Chats.insert {
                it[userId] = "admin"
                it[name] = "First chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Second chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Third chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Fourth chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Fifth chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Sixth chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Seventh chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Eighth chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Ninth chat"
                it[dateUpdated] = System.currentTimeMillis()
            }

            Chats.insert {
                it[userId] = "admin"
                it[name] = "Tenth chat"
                it[dateUpdated] = System.currentTimeMillis()
            }
        }
    }
}
