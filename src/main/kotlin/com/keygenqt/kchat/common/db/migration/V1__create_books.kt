package com.keygenqt.kchat.common.db.migration

import com.keygenqt.kchat.common.db.models.Books
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

@Suppress("unused")
class V1__create_books : BaseJavaMigration() {
    override fun migrate(context: Context?) {
        transaction {
            SchemaUtils.create(Books)

            Books.insert {
                it[title] = "Book one"
                it[author] = "Chuck Palahniuk"
                it[dateUpdated] = System.currentTimeMillis()
            }
            Books.insert {
                it[title] = "Book two"
                it[author] = "William Shakespeare"
                it[dateUpdated] = System.currentTimeMillis()
            }
        }
    }
}
