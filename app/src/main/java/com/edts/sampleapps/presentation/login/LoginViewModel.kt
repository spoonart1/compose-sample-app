package com.edts.sampleapps.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val login = MutableLiveData<LoginModel>()
    val loginData: LiveData<LoginModel>
        get() = login

    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState>
        get() = _state

    fun doLogin(loginModel: LoginModel) {
        this.login.value = loginModel
        setLoginState(LoginState.Loading())
        viewModelScope.launch {
            delay(1000) //mock api response
            if (!emailCheck(loginModel)) {
                return@launch
            }

            if (!passwordCheck(loginModel)) {
                return@launch
            }

            setLoginState(LoginState.Success())
        }
    }

    private fun setLoginState(loginState: LoginState) {
        _state.value = loginState
    }

    private fun emailCheck(loginModel: LoginModel): Boolean =
        if (loginModel.email.isNullOrEmpty()) {
            setLoginState(LoginState.ErrorEmail("email cannot be blank or empty"))
            false
        } else if (loginModel.email != "username") {
            setLoginState(LoginState.ErrorEmail("incorrect email or phone number use 'username' instead"))
            false
        } else {
            true
        }

    private fun passwordCheck(loginModel: LoginModel): Boolean =
        if (loginModel.password.isNullOrEmpty()) {
            setLoginState(LoginState.ErrorPassword("password cannot be blank or empty"))
            false
        } else if (loginModel.password != "12345") {
            setLoginState(LoginState.ErrorPassword("incorrect password"))
            false
        } else {
            true
        }
}