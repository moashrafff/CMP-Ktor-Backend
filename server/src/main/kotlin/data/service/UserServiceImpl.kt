package com.moashrafff.data.service

import com.moashrafff.domain.model.User
import com.moashrafff.domain.model.request.LoginRequest
import com.moashrafff.domain.model.request.RegisterRequest
import com.moashrafff.domain.repo.UserRepository
import com.moashrafff.domain.service.UserService

class UserServiceImpl(val userRepository: UserRepository) : UserService {
    override suspend fun registerUser(registerRequest: RegisterRequest): User? =
        userRepository.createUser(registerRequest = registerRequest)

    override suspend fun loginUser(loginRequest: LoginRequest): User? =
        userRepository.login(request = loginRequest)

    override suspend fun getUserById(id: Long): User? = userRepository.getUserById(id = id)

}