package org.moashrafff.showcase.presentation.features.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.moashrafff.showcase.data.cache.ShowcaseSession
import org.moashrafff.showcase.data.dto.request.RegisterRequest
import org.moashrafff.showcase.data.network.ResultWrapper
import org.moashrafff.showcase.data.network.UserService
import org.moashrafff.showcase.presentation.features.register.RegisterState.*

class RegisterViewModel(val networkService: UserService, private val session: ShowcaseSession) :
    ViewModel() {
    private val _uiState = MutableStateFlow<RegisterState>(Nothing)
    val uiState = _uiState.asStateFlow()

    fun register(name: String, email: String, password: String) {
        _uiState.value = Loading
        viewModelScope.launch {
            val response = networkService.register(
                RegisterRequest(
                    email = email,
                    password = password,
                    name = name
                )
            )
            when (response) {
                is ResultWrapper.Success -> {
                    _uiState.value = Success
                    session.saveToken(response.value.token)
                }

                is ResultWrapper.Error -> {
                    _uiState.value = Error(response.exception.message ?: "An error occurred")
                }

                ResultWrapper.InProgress -> TODO()
            }

        }

    }

    fun retry() {
        _uiState.value = Nothing
    }
}

sealed class RegisterState {
    object Nothing : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}