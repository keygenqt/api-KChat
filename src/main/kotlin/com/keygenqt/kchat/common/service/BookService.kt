package com.keygenqt.kchat.common.service

import com.keygenqt.kchat.common.config.DBConfig.dbQuery
import com.keygenqt.kchat.common.db.ChangeType
import com.keygenqt.kchat.common.db.Notification
import com.keygenqt.kchat.common.db.models.Book
import com.keygenqt.kchat.common.db.models.Books
import com.keygenqt.kchat.common.db.models.NewBook
import org.jetbrains.exposed.sql.*

class BookService {

    private val listeners = mutableMapOf<Int, suspend (Notification<Book?>) -> Unit>()

    fun addChangeListener(id: Int, listener: suspend (Notification<Book?>) -> Unit) {
        listeners[id] = listener
    }

    fun removeChangeListener(id: Int) = listeners.remove(id)

    private suspend fun onChange(type: ChangeType, id: Int, entity: Book? = null) {
        listeners.values.forEach {
            it.invoke(Notification(type, id, entity))
        }
    }

    suspend fun getAllBooks(): List<Book> = dbQuery {
        Books.selectAll().map { toBook(it) }
    }

    suspend fun getBook(id: Int): Book? = dbQuery {
        Books.select {
            (Books.id eq id)
        }.mapNotNull { toBook(it) }
            .singleOrNull()
    }

    suspend fun updateBook(Book: NewBook): Book? {
        val id = Book.id
        return if (id == null) {
            addBook(Book)
        } else {
            dbQuery {
                Books.update({ Books.id eq id }) {
                    it[title] = Book.title
                    it[author] = Book.author
                    it[dateUpdated] = System.currentTimeMillis()
                }
            }
            getBook(id).also {
                onChange(ChangeType.UPDATE, id, it)
            }
        }
    }

    suspend fun addBook(Book: NewBook): Book {
        var key = 0
        dbQuery {
            key = (Books.insert {
                it[title] = Book.title
                it[author] = Book.author
                it[dateUpdated] = System.currentTimeMillis()
            } get Books.id)
        }
        return getBook(key)!!.also {
            onChange(ChangeType.CREATE, key, it)
        }
    }

    suspend fun deleteBook(id: Int): Boolean {
        return dbQuery {
            Books.deleteWhere { Books.id eq id } > 0
        }.also {
            if (it) onChange(ChangeType.DELETE, id)
        }
    }

    private fun toBook(row: ResultRow): Book =
        Book(
            id = row[Books.id],
            title = row[Books.title],
            author = row[Books.author],
            dateUpdated = row[Books.dateUpdated]
        )
}