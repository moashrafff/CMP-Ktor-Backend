package org.moashrafff.showcase.data.dto.response

import kotlinx.serialization.Serializable
import org.moashrafff.showcase.data.dto.User

@Serializable
data class RegisterResponse(val user: User, val token: String)