package com.example.ui.otp

import androidx.lifecycle.viewModelScope
import com.example.domain.authentication.OtpValidationUseCase
import com.example.domain.profile.CustomizeProfileSettingsUseCase
import com.example.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
    private val otpValidationUseCase: OtpValidationUseCase
) : BaseViewModel<OtpUiState, OtpEffects>(OtpUiState()),
    IOtpInteractions {

    init {
        viewModelScope.launch { isDarkTheme() }
    }

    private suspend fun isDarkTheme() {
        customizeProfileSettings.isDarkThem().buffer().collect{ isDark ->
            updateData {
                copy(isDarkTheme = isDark)
            }
        }
    }


    override fun onOtpChange(newOtp: String) {
        updateData {
            copy(
                otp = newOtp,
                isConfirmButtonEnabled = newOtp.replace(" ", "").length == otpLength
            )
        }

    }


    override fun onClickConfirm() {
        tryToExecute(
            call = {
                otpValidationUseCase(
                    otp = state.value.data.otp,
                )
            },
            onSuccess = { navigateToHome() },
        )
    }

    private fun navigateToHome() {
        navigateTo(OtpEffects.NavigateToHome)
    }

}

