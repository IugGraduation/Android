package com.example.ui.signup

import androidx.lifecycle.ViewModel
import com.example.domain.LoginValidationUseCase
import com.example.domain.SignupValidationUseCase
import com.example.domain.models.SignStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupValidationUseCase: SignupValidationUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignupUIState())
    val uiState = _uiState.asStateFlow()

    fun onFullNameChange(newValue: String) {
        _uiState.update { it.copy(fullName = newValue) }
    }

    fun onPhoneChange(newValue: String) {
        _uiState.update { it.copy(phone = newValue) }
    }

    fun onPasswordChange(newValue: String) {
        _uiState.update { it.copy(password = newValue) }
    }

    fun onConfirmPasswordChange(newValue: String) {
        _uiState.update { it.copy(confirmPassword = newValue) }
    }

    fun toggleConfirmPasswordVisibility() {
        _uiState.update { it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible) }
    }

    fun onBestBarterSpotChange(newValue: String) {
        _uiState.update { it.copy(bestBarterSpot = newValue) }
    }

    fun onBioChange(newValue: String) {
        _uiState.update { it.copy(bio = newValue) }
    }

    fun onClickSignup() {
        if (signupValidationUseCase(uiState.value.toSignStatus()).isSuccess()) {
            //Signup
        }
    }


}

