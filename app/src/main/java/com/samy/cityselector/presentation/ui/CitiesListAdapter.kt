package com.samy.cityselector.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.samy.cityselector.presentation.entities.CityViewEntityItem

class CitiesListAdapter :
    ListAdapter<CityViewEntityItem, CityViewHolder>(CityViewEntityItemDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CityViewEntityItemDiffUtils : DiffUtil.ItemCallback<CityViewEntityItem>() {
    override fun areItemsTheSame(
        oldItem: CityViewEntityItem,
        newItem: CityViewEntityItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CityViewEntityItem,
        newItem: CityViewEntityItem
    ): Boolean {
        return oldItem.title == newItem.supTitle
    }


}
