package org.moashrafff.showcase.presentation.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.moashrafff.showcase.data.cache.ShowcaseSession
import org.moashrafff.showcase.data.dto.request.LoginRequest
import org.moashrafff.showcase.data.network.ResultWrapper
import org.moashrafff.showcase.data.network.UserService
import org.moashrafff.showcase.presentation.features.login.LoginState.*

class LoginViewModel(val networkService: UserService, private val session: ShowcaseSession) : ViewModel() {
    val _uiState = MutableStateFlow<LoginState>(Nothing)
    val uiState = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        _uiState.value = Loading
        viewModelScope.launch {
            val result = networkService.login(LoginRequest(email, password))
            when (result) {
                is ResultWrapper.Success -> {
                    _uiState.value = Success
                    session.saveToken(result.value.token)
                }

                is ResultWrapper.Error -> {
                    _uiState.value = Error(result.exception.message ?: "An error occurred")
                }

                ResultWrapper.InProgress -> TODO()
            }
        }
    }

    fun retry() {
        _uiState.value = Nothing
    }

}

sealed class LoginState {
    object Nothing : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}