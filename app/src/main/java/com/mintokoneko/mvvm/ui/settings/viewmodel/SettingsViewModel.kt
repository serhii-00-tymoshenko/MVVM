package com.mintokoneko.mvvm.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import com.mintokoneko.mvvm.data.Credentials
import com.mintokoneko.mvvm.repositories.LoginRepository

class SettingsViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {
    fun logOut() {
        loginRepository.putCredentials(Credentials())
    }
}