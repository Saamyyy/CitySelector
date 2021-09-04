package com.samy.cityselector.presentation.ui.citymap

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.samy.cityselector.R

class CityMapFragment : Fragment() {
    private val args: CityMapFragmentArgs by navArgs()

    private val callback = OnMapReadyCallback { googleMap ->
        val currentCity = LatLng(args.lat.toDouble(), args.lon.toDouble())
        googleMap.addMarker(MarkerOptions().position(currentCity).title(args.cityName))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentCity))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}
