package com.moashrafff.data.database.factory

import com.moashrafff.data.database.tables.UserTable
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

    fun Application.initDb() {
        val url = environment.config.property("database.url").getString()
        val driver = environment.config.property("database.driver").getString()
        val user = environment.config.property("database.user").getString()
        // This variable should be added to the environment variables
        val password = System.getenv().get("ktor_showcase_db_password")

        val db = Database.connect(
            url = url, driver = driver, user = user
        )

        transaction(db = db) {
            SchemaUtils.create(UserTable)
            TransactionManager.manager.defaultIsolationLevel =
                java.sql.Connection.TRANSACTION_SERIALIZABLE
        }
    }
