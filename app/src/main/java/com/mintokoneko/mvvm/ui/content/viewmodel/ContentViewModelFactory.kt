package com.mintokoneko.mvvm.ui.content.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mintokoneko.mvvm.repositories.ContentRepository

class ContentViewModelFactory(
    private val contentRepository: ContentRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ContentViewModel::class.java)) {
            return ContentViewModel(contentRepository) as T
        }

        throw IllegalArgumentException("Wrong viewModel")
    }
}