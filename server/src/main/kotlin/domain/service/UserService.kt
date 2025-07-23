package com.moashrafff.domain.service

import com.moashrafff.domain.model.User
import com.moashrafff.domain.model.request.LoginRequest
import com.moashrafff.domain.model.request.RegisterRequest

interface UserService {
    suspend fun registerUser(registerRequest: RegisterRequest): User?
    suspend fun loginUser(loginRequest: LoginRequest): User?
    suspend fun getUserById(id: Long): User?
}