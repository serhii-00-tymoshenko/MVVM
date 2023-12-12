package com.mintokoneko.mvvm.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mintokoneko.mvvm.repositories.LoginRepository

class SettingsViewModelFactory(
    private val loginRepository: LoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(loginRepository) as T
        }

        throw IllegalArgumentException("Wrong viewModel")
    }
}