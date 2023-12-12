package com.mintokoneko.mvvm.ui.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintokoneko.mvvm.data.Country
import com.mintokoneko.mvvm.databinding.FragmentCountriesBinding
import com.mintokoneko.mvvm.ui.content.viewmodel.ContentViewModel
import com.mintokoneko.mvvm.ui.content.viewmodel.ContentViewModelProvider
import com.mintokoneko.mvvm.ui.countries.adapters.CountryAdapter

class CountriesFragment : Fragment() {
    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var contentViewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val parentFragment = requireParentFragment()

        setContentViewModel(parentFragment, context)
        initObservers()
        setupRecycler(context)
    }

    private fun initObservers() {
        contentViewModel.countries.observe(viewLifecycleOwner) { countries ->
            countryAdapter.submitList(countries)
        }
    }

    private fun setContentViewModel(fragment: Fragment, context: Context) {
        contentViewModel = ContentViewModelProvider.getViewModel(fragment, context)
    }

    private fun setupRecycler(context: Context) {
        countryAdapter = CountryAdapter { country -> setCurrentCountry(country) }

        val countriesRecycler = binding.countriesRecycler
        countriesRecycler.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun setCurrentCountry(country: Country) {
        contentViewModel.setCurrentCountry(country)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}