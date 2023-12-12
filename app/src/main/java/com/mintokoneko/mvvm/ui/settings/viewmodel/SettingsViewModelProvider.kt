package com.mintokoneko.mvvm.ui.settings.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mintokoneko.mvvm.repositories.LoginRepository

class SettingsViewModelProvider {
    companion object {
        fun getViewModel(owner: ViewModelStoreOwner, context: Context): SettingsViewModel =
            ViewModelProvider(
                owner, SettingsViewModelFactory(LoginRepository.getInstance(context))
            )[SettingsViewModel::class.java]
    }
}