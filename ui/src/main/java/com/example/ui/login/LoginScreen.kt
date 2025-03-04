package com.example.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ui.signup.navigateToSignup

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val state by loginViewModel.state.collectAsState()

    LoginContent(
        state = state,
        onClickLogin = loginViewModel::onClickLogin,
        onPhoneChange = loginViewModel::onPhoneChange,
        onPasswordChange = loginViewModel::onPasswordChange,
        togglePasswordVisibility = loginViewModel::togglePasswordVisibility,
        onClickGoToSignup = { navController.navigateToSignup() }
    )
}

