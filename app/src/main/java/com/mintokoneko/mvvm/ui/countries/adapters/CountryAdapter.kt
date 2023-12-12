package com.mintokoneko.mvvm.ui.countries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mintokoneko.mvvm.data.Country
import com.mintokoneko.mvvm.databinding.ItemCountryBinding

class CountryAdapter(
    private val onItemClick: (Country) -> Unit
) : ListAdapter<Country, CountryAdapter.CountryViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Country, newItem: Country) =
                oldItem == newItem
        }
    }

    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.root.setOnClickListener { onItemClick(country) }
            }
            binding.countryTitle.text = country.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentCountry = getItem(position)
        holder.bind(currentCountry)
    }

}