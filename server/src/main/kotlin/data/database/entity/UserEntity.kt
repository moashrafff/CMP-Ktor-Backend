package com.moashrafff.data.database.entity

import com.moashrafff.data.database.tables.UserTable
import com.moashrafff.domain.model.User
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id : EntityID<Long>) : LongEntity(id = id) {
    companion object : LongEntityClass<UserEntity>(UserTable)

    var email by UserTable.email
    var name by UserTable.name
    var password by UserTable.password

    fun toUser() = User(id = id.value, userName = name, email = email)

}