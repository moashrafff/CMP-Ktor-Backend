package com.moashrafff.data.repo

import com.moashrafff.data.database.entity.UserEntity
import com.moashrafff.data.database.tables.UserTable
import com.moashrafff.domain.model.User
import com.moashrafff.domain.model.request.LoginRequest
import com.moashrafff.domain.model.request.RegisterRequest
import com.moashrafff.domain.repo.UserRepository
import com.moashrafff.utils.dbQuery
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImpl : UserRepository {
    override suspend fun createUser(registerRequest: RegisterRequest): User? {
        return dbQuery {
            val existingUser =
                UserEntity.find { UserTable.email eq registerRequest.email }.firstOrNull()
            if (existingUser != null) {
                return@dbQuery null
            }
            val newUser = UserEntity.new {
                this.name = registerRequest.name
                this.email = registerRequest.email
                this.password = registerRequest.password
            }
            return@dbQuery newUser.toUser()
        }
    }

    override suspend fun getUserById(id: Long): User? {
        return dbQuery {
            UserEntity.findById(id = id)?.toUser()
        }
    }

    override suspend fun login(request: LoginRequest): User? {
        return dbQuery {
            UserEntity.find { UserTable.email eq request.email }.firstOrNull()
                ?.takeIf { it.password == request.password }?.toUser()
        }
    }

    override fun hashPassword(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())

    override fun verifyPassword(
        plainPassword: String,
        hashedPassword: String
    ): Boolean = BCrypt.checkpw(plainPassword, hashedPassword)
}