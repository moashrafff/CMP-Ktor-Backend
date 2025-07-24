package org.moashrafff.showcase.data.network

import org.moashrafff.showcase.data.dto.request.LoginRequest
import org.moashrafff.showcase.data.dto.request.RegisterRequest
import org.moashrafff.showcase.data.dto.response.LoginResponse
import org.moashrafff.showcase.data.dto.response.RegisterResponse

interface UserService {
    suspend fun register(request: RegisterRequest): ResultWrapper<RegisterResponse>
    suspend fun login(request: LoginRequest): ResultWrapper<LoginResponse>
}