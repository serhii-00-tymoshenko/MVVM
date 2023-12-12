package com.mintokoneko.mvvm.repositories

import android.content.Context
import com.mintokoneko.mvvm.data.Credentials
import com.mintokoneko.mvvm.repositories.preferences.AppSharedPreferences

class LoginRepository(context: Context) {
    private val sharedPreferences by lazy {
        AppSharedPreferences.getInstance(context)
    }

    companion object {
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(context).also { instance = it }
            }
    }

    fun putCredentials(credentials: Credentials) = sharedPreferences.putCredentials(credentials)

    fun getCredentials(): Credentials = sharedPreferences.getCredentials()
}