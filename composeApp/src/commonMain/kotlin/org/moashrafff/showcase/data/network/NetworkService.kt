package org.moashrafff.showcase.data.network

import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import org.moashrafff.showcase.data.dto.request.LoginRequest
import org.moashrafff.showcase.data.dto.request.RegisterRequest
import org.moashrafff.showcase.data.dto.response.LoginResponse
import org.moashrafff.showcase.data.dto.response.RegisterResponse

class NetworkService(val httpClient: HttpClient) : UserService {
    val baseUrl = "https://localhost:8080"

    override suspend fun register(request: RegisterRequest): ResultWrapper<RegisterResponse> {
        return httpClient.makeNetworkRequest<RegisterResponse>(
            "$baseUrl/api/users/register",
            HttpMethod.Post,
            body = request
        )
    }

    override suspend fun login(request: LoginRequest): ResultWrapper<LoginResponse> =
        httpClient.makeNetworkRequest<LoginResponse>(
            "$baseUrl/api/users/login",
            HttpMethod.Post,
            body = request
        )

}

