package com.moashrafff.data.database.tables

import org.jetbrains.exposed.dao.id.LongIdTable

const val MAX_VARCHAR_LENGTH = 255

object UserTable : LongIdTable() {
    val email = varchar("email", MAX_VARCHAR_LENGTH).uniqueIndex()
    val password = varchar("password", MAX_VARCHAR_LENGTH)
    val name = varchar("name", MAX_VARCHAR_LENGTH)
}