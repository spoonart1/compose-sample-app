package com.edts.sampleapps.presentation.login

data class LoginModel(
    val email: String?,
    val password: String?
)

sealed class LoginState(val message: String?) {
    data class ErrorEmail(val data: String) : LoginState(data)
    data class ErrorPassword(val data: String) : LoginState(data)
    data class ErrorNetwork(val data: String) : LoginState(data)
    class Loading : LoginState(null)
    class Success : LoginState(null)
}