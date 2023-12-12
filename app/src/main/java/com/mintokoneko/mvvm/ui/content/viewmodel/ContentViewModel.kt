package com.mintokoneko.mvvm.ui.content.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mintokoneko.mvvm.data.Country
import com.mintokoneko.mvvm.repositories.ContentRepository

class ContentViewModel(
    private val contentRepository: ContentRepository
) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _currentCountry = MutableLiveData<Country>()
    val currentCountry: LiveData<Country> get() = _currentCountry

    init {
        val data = getDataFromRepository()
        setCountries(data)
        putDataToRepository(data)
    }

    private fun getDataFromRepository(): List<Country> = contentRepository.getData()

    private fun setCountries(data: List<Country>) {
        _countries.value = data
    }

    private fun putDataToRepository(data: List<Country>) {
        contentRepository.putCountries(data)
    }

    fun setCurrentCountry(country: Country) {
        _currentCountry.value = country
    }
}