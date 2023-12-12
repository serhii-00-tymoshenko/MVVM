package com.mintokoneko.mvvm.repositories

import android.content.Context
import com.mintokoneko.mvvm.data.Country
import com.mintokoneko.mvvm.repositories.preferences.AppSharedPreferences

class ContentRepository(context: Context) {
    private val sharedPreferences by lazy {
        AppSharedPreferences.getInstance(context)
    }

    companion object {
        @Volatile
        private var instance: ContentRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: ContentRepository(context).also { instance = it }
            }
    }

    private fun fetchData(): List<Country> = listOf(
        Country("A", 0),
        Country("B", 1),
        Country("C", 2),
        Country("D", 3),
        Country("E", 4),
        Country("F", 5),
        Country("G", 6),
        Country("H", 7),
        Country("I", 8),
        Country("J", 9),
    )

    private fun getCountries(): List<Country> = sharedPreferences.getCountries()

    fun getData(): List<Country> {
        var data = getCountries()
        data.ifEmpty {
            data = fetchData()
        }

        return data
    }

    fun putCountries(countries: List<Country>) = sharedPreferences.putCountries(countries)
}