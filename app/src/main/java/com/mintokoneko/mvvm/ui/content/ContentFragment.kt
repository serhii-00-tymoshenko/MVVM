package com.mintokoneko.mvvm.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mintokoneko.mvvm.R
import com.mintokoneko.mvvm.databinding.FragmentContentBinding

class ContentFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOpenSettingsListener()
    }

    private fun setOpenSettingsListener() {
        binding.openSettings.setOnClickListener {
            beginSettingsTransaction()
        }
    }

    private fun beginSettingsTransaction() {
        val settingsAction = R.id.action_contentFragment_to_settingsFragment
        findNavController().navigate(settingsAction)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}