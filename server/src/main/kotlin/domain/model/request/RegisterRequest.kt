package com.moashrafff.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val userName : String,
    val password : String
)
