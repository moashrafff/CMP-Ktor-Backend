package com.moashrafff.plugins

import com.moashrafff.config.generateToken
import com.moashrafff.domain.model.request.LoginRequest
import com.moashrafff.domain.model.request.RegisterRequest
import com.moashrafff.domain.model.response.LoginResponse
import com.moashrafff.domain.service.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting(userService: UserService) {
    install(StatusPages) {
        exception<IllegalArgumentException> { call, cause ->
            call.respondText(text = cause.message.orEmpty(), status = HttpStatusCode.BadRequest)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        route("/api/users") {
            post("/register") {
                val request = call.receive<RegisterRequest>()
                userService.registerUser(registerRequest = request)?.let { user ->
                    call.respond(HttpStatusCode.Created, user)
                } ?: call.respond(HttpStatusCode.Conflict, "User name already exists")
            }
            post("/login") {
                val request = call.receive<LoginRequest>()
                userService.loginUser(loginRequest = request)?.let { user ->
                    generateToken(user = user)?.let { token ->
                        call.respond(HttpStatusCode.OK, LoginResponse(user = user, token = token))
                    } ?: call.respond(HttpStatusCode.Unauthorized, "Token generation failed")
                } ?: call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }
            get("/id") {
                val id = call.parameters["id"]?.toLongOrNull()
                    ?: return@get call.respond(HttpStatusCode.Conflict, "Invalid Id")

                userService.getUserById(id)?.let {user ->
                    call.respond(HttpStatusCode.OK, user)
                } ?: call.respond(HttpStatusCode.Conflict, "User Not Found")

            }
        }

    }
}
