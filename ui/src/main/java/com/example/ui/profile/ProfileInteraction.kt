package com.example.ui.profile

import android.net.Uri

interface ProfileInteraction {
    fun onUpdateProfileImage(imageUri: Uri)
    fun onEditButtonClicked()
    fun onUsernameChange(newName: String)
    fun onPhoneNumberChange(newNumber: String)
    fun onLocationChange(location: String)
    fun onBioChange(bio: String)
    fun onCancelButtonClicked()
    fun onSaveButtonClicked()
    fun onDarkMoodChange(isDarkMood: Boolean)
    fun onLogoutClicked()
    fun onResetPasswordClicked()
    fun onChangeLanguageClicked()
    fun onUpdateLogoutDialogState(showDialog: Boolean)

}