package com.mintokoneko.mvvm.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mintokoneko.mvvm.databinding.FragmentDetailsBinding
import com.mintokoneko.mvvm.ui.content.viewmodel.ContentViewModel
import com.mintokoneko.mvvm.ui.content.viewmodel.ContentViewModelProvider

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var contentViewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val parentFragment = requireParentFragment()

        setContentViewModel(parentFragment, context)
        initObservers()
    }

    private fun setContentViewModel(fragment: Fragment, context: Context) {
        contentViewModel = ContentViewModelProvider.getViewModel(fragment, context)
    }

    private fun initObservers() {
        contentViewModel.currentCountry.observe(viewLifecycleOwner) { country ->
            binding.countryName.text = country.name
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}