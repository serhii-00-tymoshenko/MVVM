package com.mintokoneko.mvvm.repositories.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mintokoneko.mvvm.data.Country
import com.mintokoneko.mvvm.data.Credentials
import com.mintokoneko.mvvm.utils.COUNTRIES_PREFERENCE_NAME
import com.mintokoneko.mvvm.utils.CREDENTIALS_PREFERENCE_NAME
import com.mintokoneko.mvvm.utils.SHARED_PREFERENCES_NAME

class AppSharedPreferences(context: Context) {
    private val gson = Gson()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)


    companion object {
        @Volatile
        private var instance: AppSharedPreferences? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AppSharedPreferences(context).also { instance = it }
            }
    }

    private inline fun <reified T> Gson.fromJson(json: String?): T =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    fun putCredentials(credentials: Credentials) {
        val jsonData = gson.toJson(credentials)
        sharedPreferences
            .edit()
            .putString(CREDENTIALS_PREFERENCE_NAME, jsonData)
            .apply()
    }

    fun getCredentials(): Credentials {
        val jsonData = sharedPreferences.getString(CREDENTIALS_PREFERENCE_NAME, null)
        val credentials = gson.fromJson(jsonData, Credentials::class.java) ?: Credentials()
        return credentials
    }

    fun putCountries(countries: List<Country>) {
        val jsonData = gson.toJson(countries)
        sharedPreferences
            .edit()
            .putString(COUNTRIES_PREFERENCE_NAME, jsonData)
            .apply()
    }

    fun getCountries(): List<Country> {
        val jsonData = sharedPreferences.getString(COUNTRIES_PREFERENCE_NAME, null)
        val countries = gson.fromJson<List<Country>>(jsonData)
        return countries
    }
}