package com.moashrafff.plugins

import com.moashrafff.config.jwtVerifier
import com.moashrafff.domain.service.UserService
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt

fun Application.configureSecurity(userService: UserService) {
    // Please read the jwt property from the config file if you are using EngineMain
    val jwtRealm = environment.config.property("jwt.realm").getString()
    val jwtClaimField = environment.config.property("jwt.claimField").getString()

    val verifier = jwtVerifier()

    install(Authentication) {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(verifier)
            validate { credential ->
                credential.payload.getClaim(jwtClaimField).asLong()?.let { userService.getUserById(it) }
            }
        }
    }
}
