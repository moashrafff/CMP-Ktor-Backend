package org.moashrafff.showcase.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class User(val userName: String, val email: String, val id: Long)
