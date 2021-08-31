package com.samy.cityselector.presentation.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samy.cityselector.R
import com.samy.cityselector.presentation.entities.CityViewEntityItem

class VenueViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    View.inflate(parentView.context, R.layout.city_list_item, null)
) {
    private val tittle: TextView by lazy { itemView.findViewById(R.id.cityName) }
    private val supTitle: TextView by lazy { itemView.findViewById(R.id.cityLocation) }

    fun bind(cityViewEntityItem: CityViewEntityItem) {
        tittle.text = cityViewEntityItem.title
        supTitle.text = cityViewEntityItem.supTitle
    }
}
