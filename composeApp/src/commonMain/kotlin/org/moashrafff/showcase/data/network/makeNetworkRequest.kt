package org.moashrafff.showcase.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.io.IOException

suspend inline fun  <reified T> HttpClient.makeNetworkRequest (
    url: String,
    method: HttpMethod,
    body: Any? = null,
    headers: Map<String, String> = emptyMap(),
    parameters: Map<String, String> = emptyMap()
): ResultWrapper<T> {
    return try {
        val response = this.request(url) {
            this.method = method
            headers.forEach { (key, value) ->
                this.headers.append(key, value)
            }
            parameters.forEach { (key, value) ->
                this.parameter(key, value)
            }
            if (body != null) {
                this.setBody(body)
            }
            contentType(ContentType.Application.Json)
        }.body<T>()
        ResultWrapper.Success(response)
    } catch (e: ClientRequestException) {
        ResultWrapper.Error(e)
    } catch (e: ServerResponseException) {
        ResultWrapper.Error(e)
    } catch (e: IOException) {
        ResultWrapper.Error(e)
    } catch (e: Exception) {
        ResultWrapper.Error(e)
    }
}