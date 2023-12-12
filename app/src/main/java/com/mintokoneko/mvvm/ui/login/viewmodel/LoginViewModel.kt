package com.mintokoneko.mvvm.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mintokoneko.mvvm.data.Credentials
import com.mintokoneko.mvvm.repositories.LoginRepository

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _credentials = MutableLiveData(loginRepository.getCredentials())
    val credentials: LiveData<Credentials> get() = _credentials

    init {
        val credentials = getCredentialsFromRepository()
        setCredentials(credentials)
    }

    private fun getCredentialsFromRepository(): Credentials = loginRepository.getCredentials()

    private fun putCredentialsToRepository(credentials: Credentials) =
        loginRepository.putCredentials(credentials)

    private fun setCredentials(credentials: Credentials) {
        _credentials.value = credentials
    }

    fun changeCredentials(credentials: Credentials) {
        putCredentialsToRepository(credentials)
        setCredentials(credentials)
    }
}