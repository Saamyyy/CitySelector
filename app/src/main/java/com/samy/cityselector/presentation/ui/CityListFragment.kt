package com.samy.cityselector.presentation.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.samy.cityselector.R
import com.samy.cityselector.presentation.entities.CityListViewStates
import com.samy.cityselector.presentation.viewmodel.CitiesListVIewModel
import kotlinx.android.synthetic.main.fragment_city_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class CityListFragment : Fragment() {
    private val citiesListViewModel by viewModel<CitiesListVIewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_city_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        citiesListViewModel.onViewCreated()
        setHasOptionsMenu(true)
        observeLiveData()
    }

    private fun observeLiveData() {
        citiesListViewModel.cityListViewStates.observe(viewLifecycleOwner) { handleViewState(it) }
    }

    private fun handleViewState(state: CityListViewStates) {
        when (state) {
            is CityListViewStates.Loading -> handleLoading()
            is CityListViewStates.Empty -> handleEmpty()
            is CityListViewStates.Error -> handleError(state)
            is CityListViewStates.Content -> handleContent(state)
        }
    }

    private fun handleContent(state: CityListViewStates.Content) {
        cityList.layoutManager = LinearLayoutManager(context)
        val adapter = CitiesListAdapter(state.citiesListViewEntity)
        cityList.adapter = adapter
        cityList.visibility = View.VISIBLE
        listError.visibility = View.GONE
        listLoader.visibility = View.GONE
    }

    private fun handleError(state: CityListViewStates.Error) {
        listError.text = getString(R.string.general_error)
        cityList.visibility = View.GONE
        listError.visibility = View.VISIBLE
        listLoader.visibility = View.GONE
    }

    private fun handleEmpty() {
        listError.text = getString(R.string.empty_error)
        cityList.visibility = View.GONE
        listError.visibility = View.VISIBLE
        listLoader.visibility = View.GONE
    }

    private fun handleLoading() {
        cityList.visibility = View.GONE
        listError.visibility = View.GONE
        listLoader.visibility = View.VISIBLE
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
