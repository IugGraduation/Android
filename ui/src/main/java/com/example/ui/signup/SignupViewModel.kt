package com.example.ui.signup

import com.example.domain.SignupValidationUseCase
import com.example.domain.exception.InvalidBestBarterSpotErrorException
import com.example.domain.exception.InvalidConfirmPasswordException
import com.example.domain.exception.InvalidFullNameException
import com.example.domain.exception.InvalidPasswordException
import com.example.domain.exception.InvalidPhoneException
import com.example.domain.exception.PasswordMismatchException
import com.example.ui.base.BaseViewModel
import com.example.ui.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val stringsResource: StringsResource,
    private val signupValidationUseCase: SignupValidationUseCase,
) : BaseViewModel<SignupUiState>(SignupUiState()), ISignupInteractions {

    override fun onClickSignup() {
        tryToExecute(
            call = {
                signupValidationUseCase(
                    fullName = state.value.data.fullName,
                    phone = state.value.data.phone,
                    password = state.value.data.password,
                    confirmPassword = state.value.data.confirmPassword,
                    bestBarterSpot = state.value.data.bestBarterSpot,
                )
            },
            onSuccess = { navigateToConfirmNumber() },
            onError = ::onSignupFail
        )
    }

    private fun navigateToConfirmNumber() {
        updateData {
            copy(shouldNavigateToConfirmNumber = true)
        }
    }

    private fun onSignupFail(throwable: Throwable) {
        when (throwable) {
            is InvalidFullNameException -> {
                updateFieldError(fullNameError = stringsResource.invalidPhoneNumber)
            }

            is InvalidPhoneException -> {
                updateFieldError(phoneError = stringsResource.invalidPhoneNumber)
            }

            is InvalidPasswordException -> {
                updateFieldError(passwordError = stringsResource.invalidPassword)
            }

            is InvalidConfirmPasswordException -> {
                updateFieldError(confirmPasswordError = stringsResource.invalidPassword)
            }

            is InvalidBestBarterSpotErrorException -> {
                updateFieldError(bestBarterSpotError = stringsResource.invalidBestBarterSpot)
            }

            is PasswordMismatchException -> {
                updateFieldError(
                    passwordError = stringsResource.passwordMismatch,
                    confirmPasswordError = stringsResource.passwordMismatch
                )
            }

            else -> onActionFail(throwable)
        }
    }

    private fun updateFieldError(
        fullNameError: String = "",
        phoneError: String = "",
        passwordError: String = "",
        confirmPasswordError: String = "",
        bestBarterSpotError: String = "",
    ) {
        updateData {
            copy(
                signupError = SignupErrorUiState(
                    fullNameError = fullNameError,
                    phoneError = phoneError,
                    passwordError = passwordError,
                    confirmPasswordError = confirmPasswordError,
                    bestBarterSpotError = bestBarterSpotError,
                )
            )
        }
    }


    override fun onFullNameChange(newValue: String) {
        updateFieldError()
        updateData {
            copy(fullName = newValue)
        }
    }

    override fun onPhoneChange(newValue: String) {
        updateFieldError()
        updateData {
            copy(phone = newValue)
        }
    }

    override fun onPasswordChange(newValue: String) {
        updateFieldError()
        updateData {
            copy(password = newValue)
        }
    }

    override fun togglePasswordVisibility() {
        updateData {
            copy(isPasswordVisible = !isPasswordVisible)
        }
    }

    override fun onConfirmPasswordChange(newValue: String) {
        updateFieldError()
        updateData {
            copy(confirmPassword = newValue)
        }
    }

    override fun toggleConfirmPasswordVisibility() {
        updateData {
            copy(isConfirmPasswordVisible = !isConfirmPasswordVisible)
        }
    }

    override fun onBestBarterSpotChange(newValue: String) {
        updateFieldError()
        updateData {
            copy(bestBarterSpot = newValue)
        }
    }

    override fun onBioChange(newValue: String) {
        updateFieldError()
        updateData {
            copy(bio = newValue)
        }
    }

}

