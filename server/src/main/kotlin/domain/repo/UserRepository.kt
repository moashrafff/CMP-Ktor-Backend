package com.moashrafff.domain.repo

import com.moashrafff.domain.model.User
import com.moashrafff.domain.model.request.LoginRequest
import com.moashrafff.domain.model.request.RegisterRequest

interface UserRepository {
    suspend fun createUser(registerRequest: RegisterRequest): User?
    suspend fun getUserById(id: Int): User?
    suspend fun login(request: LoginRequest): User?
    fun hashPassword(password: String): String
    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean
}