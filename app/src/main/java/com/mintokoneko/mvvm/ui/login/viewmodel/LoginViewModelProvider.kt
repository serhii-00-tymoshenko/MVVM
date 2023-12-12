package com.mintokoneko.mvvm.ui.login.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mintokoneko.mvvm.repositories.LoginRepository

class LoginViewModelProvider {
    companion object {
        fun getViewModel(owner: ViewModelStoreOwner, context: Context): LoginViewModel =
            ViewModelProvider(
                owner, LoginViewModelFactory(LoginRepository.getInstance(context))
            )[LoginViewModel::class.java]
    }
}