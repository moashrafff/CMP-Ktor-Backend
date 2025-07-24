package com.moashrafff.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email : String,
    val name : String,
    val password : String
)
