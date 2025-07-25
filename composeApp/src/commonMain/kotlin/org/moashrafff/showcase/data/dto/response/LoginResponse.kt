package org.moashrafff.showcase.data.dto.response

import kotlinx.serialization.Serializable
import org.moashrafff.showcase.data.dto.User

@Serializable
class LoginResponse (val user: User, val token: String)