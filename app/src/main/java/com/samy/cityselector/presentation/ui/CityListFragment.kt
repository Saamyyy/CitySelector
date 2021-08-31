package com.samy.cityselector.presentation.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.samy.cityselector.R
import com.samy.cityselector.presentation.viewmodel.CitiesListVIewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CityListFragment : Fragment() {
    private val citiesListVIewModel by viewModel<CitiesListVIewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_city_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        citiesListVIewModel.onViewCreated()
        setHasOptionsMenu(true)
        observeLiveData()
    }

    private fun observeLiveData() {
        citiesListVIewModel.cityListViewStates.observe(viewLifecycleOwner) {}
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflateSearchView(inflater, menu)
    }

    private fun inflateSearchView(inflater: MenuInflater, menu: Menu) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchViewMenuItem: MenuItem = menu.findItem(R.id.search)
        val searchView = searchViewMenuItem.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(onQueryTextListener())
    }

    private fun onQueryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?) = false
        override fun onQueryTextChange(newText: String): Boolean {
//            viewModel.searchTerm.onNext(newText)
            return false
        }

    }
}
