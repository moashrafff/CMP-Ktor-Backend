package com.moashrafff.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.moashrafff.domain.model.User
import io.ktor.server.application.*
import java.util.Date

fun Application.generateToken(user: User): String? = try {
    val jwtSecret = System.getenv("jwt_secret")
    val jwtIssuer = environment.config.property("jwt.issuer").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtClaimField = environment.config.property("jwt.claimField").getString()
    val expTimeInMillis = 24 * 60 * 60 * 1000

    JWT.create().withIssuer(jwtIssuer).withAudience(jwtAudience).withClaim(jwtClaimField, user.id)
        .withExpiresAt(Date(System.currentTimeMillis() + expTimeInMillis)).sign(Algorithm.HMAC256(jwtSecret))

} catch (e: Exception) {
    e.printStackTrace()
    null
}

fun Application.jwtVerifier(): JWTVerifier {
    val jwtSecret = System.getenv("jwt_secret")
    val jwtIssuer = environment.config.property("jwt.issuer").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()

    return JWT.require(
        Algorithm.HMAC256(jwtSecret)
    ).withIssuer(jwtIssuer).withAudience(jwtAudience).build()
}
