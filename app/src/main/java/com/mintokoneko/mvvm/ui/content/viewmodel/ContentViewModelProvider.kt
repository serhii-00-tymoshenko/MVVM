package com.mintokoneko.mvvm.ui.content.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mintokoneko.mvvm.repositories.ContentRepository

class ContentViewModelProvider {
    companion object {
        fun getViewModel(owner: ViewModelStoreOwner, context: Context): ContentViewModel =
            ViewModelProvider(
                owner, ContentViewModelFactory(ContentRepository.getInstance(context))
            )[ContentViewModel::class.java]
    }
}