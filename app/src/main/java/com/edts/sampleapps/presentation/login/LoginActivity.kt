@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalUnitApi::class)

package com.edts.sampleapps.presentation.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.*
import com.edts.sampleapps.ui.theme.GreenLeafe
import com.edts.sampleapps.ui.theme.Orange
import com.edts.sampleapps.ui.theme.White
import com.edts.sampleapps.ui.util.showToast

class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(viewModel)
        }
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val loginData by viewModel.loginData.observeAsState()
    val stateLive by viewModel.state.observeAsState()

    val mContext = LocalContext.current
    val paddingStartEnd = 20.dp

    var email by remember { mutableStateOf(TextFieldValue(loginData?.email ?: "")) }
    var password by remember { mutableStateOf(TextFieldValue(loginData?.password ?: "")) }
    var rememberMe by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    var isEmailError = stateLive is LoginState.ErrorEmail
    var isPasswordError = stateLive is LoginState.ErrorPassword
    var isLoading = stateLive is LoginState.Loading

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(White)
            .fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = paddingStartEnd, end = paddingStartEnd),
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email atau Nomor Ponsel")
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedLabelColor = GreenLeafe,
                unfocusedLabelColor = Color.LightGray,
                focusedIndicatorColor = GreenLeafe,
                unfocusedIndicatorColor = Color.LightGray
            ),
            isError = isEmailError
        )
        if (isEmailError) {
            ErrorText(text = stateLive?.message!!, paddingStartEnd)
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = paddingStartEnd, end = paddingStartEnd),
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Kata Sandi")
            },
            trailingIcon = {
                val imageVector = if (showPassword) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = {
                    showPassword = !showPassword
                }, content = {
                    Icon(imageVector, contentDescription = "password-visibility")
                })
            },
            visualTransformation = if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedLabelColor = GreenLeafe,
                unfocusedLabelColor = Color.LightGray,
                focusedIndicatorColor = GreenLeafe,
                unfocusedIndicatorColor = Color.LightGray,
            ),
            isError = isPasswordError
        )
        if (isPasswordError) {
            ErrorText(text = stateLive?.message!!, paddingStartEnd)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe, onCheckedChange = {
                    rememberMe = !rememberMe
                }, colors = CheckboxDefaults.colors(
                    checkedColor = GreenLeafe
                )
            )
            Text(text = "Ingat Saya", modifier = Modifier.weight(1f))
            ClickableText(modifier = Modifier.padding(paddingStartEnd),
                text = AnnotatedString("Lupa kata sandi?"),
                style = TextStyle(color = Orange),
                onClick = {
                    showToast("Lupa kata sandi?", mContext)
                })
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = paddingStartEnd, end = paddingStartEnd),
            onClick = {
                val loginModel = LoginModel(email = email.text, password.text)
                viewModel.doLogin(loginModel)

            }, shape = RoundedCornerShape(10), colors = ButtonDefaults.buttonColors(
                containerColor = GreenLeafe,
                contentColor = White,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White
            )
        ) {
            val text = if (isLoading) {
                "Logging in ..."
            } else {
                "Masuk"
            }
            Text(text = text)
        }
    }
}

@Composable
fun ErrorText(text: String, padding: Dp) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = padding),
        text = text, style = TextStyle(color = Color.Red, fontSize = TextUnit(12f, TextUnitType.Sp))
    )
}