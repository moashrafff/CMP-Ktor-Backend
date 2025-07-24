package org.moashrafff.showcase.data.repository

import org.moashrafff.showcase.data.dto.request.LoginRequest
import org.moashrafff.showcase.data.dto.request.RegisterRequest
import org.moashrafff.showcase.data.dto.response.LoginResponse
import org.moashrafff.showcase.data.dto.response.RegisterResponse
import org.moashrafff.showcase.data.network.ResultWrapper
import org.moashrafff.showcase.data.network.UserService
import org.moashrafff.showcase.domain.repository.UserRepository

class UserRepositoryImpl(val userService: UserService): UserRepository {
    override suspend fun register(request: RegisterRequest): ResultWrapper<RegisterResponse> =
        userService.register(request = request)

    override suspend fun login(request: LoginRequest): ResultWrapper<LoginResponse> =
        userService.login(request = request)

}