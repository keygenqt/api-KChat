package com.keygenqt.kchat.common.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.slf4j.LoggerFactory
import javax.sql.DataSource

object DBConfig {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun connectAndMigrate(configPath: String) {
        log.info("Initialising database")
        val dataSource = hikari(configPath)
        Database.connect(dataSource)
        runFlyway(dataSource)
    }

    private fun hikari(configPath: String): DataSource {
        val config = HikariConfig(configPath).apply {
            driverClassName = "com.mysql.cj.jdbc.Driver"
            jdbcUrl =
                "jdbc:mysql://localhost:3306/k_chat?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
            validate()
        }
        return HikariDataSource(config)
    }

    private fun runFlyway(datasource: DataSource) {
        val flyway = Flyway.configure()
            .locations("com/keygenqt/kchat/common/db/migration")
            .dataSource(datasource)
            .load()
        try {
            flyway.info()
            flyway.migrate()
        } catch (e: Exception) {
            log.error("Exception running flyway migration", e)
            throw e
        }
        log.info("Flyway migration has finished")
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction { block() }
}