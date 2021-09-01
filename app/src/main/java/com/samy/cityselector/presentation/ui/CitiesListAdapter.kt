package com.samy.cityselector.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samy.cityselector.presentation.entities.CitiesListViewEntity

class CitiesListAdapter(private val citiesListViewEntity: CitiesListViewEntity) :
    RecyclerView.Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return citiesListViewEntity.cities.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(citiesListViewEntity.cities[position])
    }
}
