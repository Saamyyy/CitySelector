package com.samy.cityselector.presentation.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.samy.cityselector.R
import com.samy.cityselector.presentation.entities.CityViewEntityItem

class CityViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    View.inflate(parentView.context, R.layout.city_list_item, null)
) {
    private val cityItemContainer: ConstraintLayout by lazy { itemView.findViewById(R.id.cityItemContainer) }
    private val tittle: TextView by lazy { itemView.findViewById(R.id.cityName) }
    private val supTitle: TextView by lazy { itemView.findViewById(R.id.cityLocation) }

    fun bind(cityViewEntityItem: CityViewEntityItem) {
        tittle.text = cityViewEntityItem.title
        supTitle.text = cityViewEntityItem.supTitle
        cityItemContainer.setOnClickListener {
            val action = CityListFragmentDirections.actionCityListFragmentToCityMapFragment(
                lat = cityViewEntityItem.lat,
                lon = cityViewEntityItem.lon,
                cityName = cityViewEntityItem.title
            )

            itemView.findNavController().navigate(action)
        }
    }
}
