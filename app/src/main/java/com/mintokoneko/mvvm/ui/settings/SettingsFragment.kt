package com.mintokoneko.mvvm.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mintokoneko.mvvm.R
import com.mintokoneko.mvvm.databinding.FragmentSettingsBinding
import com.mintokoneko.mvvm.ui.settings.viewmodel.SettingsViewModel
import com.mintokoneko.mvvm.ui.settings.viewmodel.SettingsViewModelProvider

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()

        setSettingsViewModel(this, context)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.apply {
            logOut.setOnClickListener {
                settingsViewModel.logOut()
                beginLoginTransaction()
            }
        }
    }

    private fun beginLoginTransaction() {
        val loginAction = R.id.action_settingsFragment_to_loginFragment
        findNavController().navigate(loginAction)
    }

    private fun setSettingsViewModel(fragment: Fragment, context: Context) {
        settingsViewModel = SettingsViewModelProvider.getViewModel(fragment, context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}