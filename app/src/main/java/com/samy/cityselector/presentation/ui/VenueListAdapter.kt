package com.samy.cityselector.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samy.cityselector.presentation.entities.CitiesListViewEntity

class VenueListAdapter(private val citiesListViewEntity: CitiesListViewEntity) :
    RecyclerView.Adapter<VenueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        return VenueViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return citiesListViewEntity.cities.size
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        holder.bind(citiesListViewEntity.cities[position])
    }
}
