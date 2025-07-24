package com.moashrafff.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val userName: String,
    val email: String
)