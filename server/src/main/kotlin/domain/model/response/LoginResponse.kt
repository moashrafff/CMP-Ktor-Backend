package com.moashrafff.domain.model.response

import com.moashrafff.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val user: User,
    val token: String
)