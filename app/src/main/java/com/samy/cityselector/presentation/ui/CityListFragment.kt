package com.samy.cityselector.presentation.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.samy.cityselector.R
import com.samy.cityselector.presentation.entities.CityListAction
import com.samy.cityselector.presentation.entities.CityListViewStates
import com.samy.cityselector.presentation.entities.NoResultFound
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
        setHasOptionsMenu(true)
        observeLiveData()
    }

    private fun observeLiveData() {
        val adapter = createListAdapter()
        citiesListViewModel.cityListViewStates.observe(viewLifecycleOwner) {
            renderUiStates(
                it,
                adapter
            )
        }
    }

    private fun createListAdapter(): CitiesListAdapter {
        cityList.layoutManager = LinearLayoutManager(context)
        val adapter = CitiesListAdapter()
        cityList.adapter = adapter
        return adapter
    }

    private fun renderUiStates(
        it: CityListViewStates,
        adapter: CitiesListAdapter
    ) {
        listError.text = if (it.error != null) getErrorMessage(it.error) else null
        listLoader.isVisible = it.isProgress
        adapter.submitList(it.citiesListViewEntity.cities)
    }

    private fun getErrorMessage(error: Throwable) = when (error) {
        is NoResultFound -> getString(R.string.empty_error)
        else -> getString(R.string.general_error)
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
                pushCityListAction(newText)
            return false
        }
    }

    private fun pushCityListAction(
        newText: String
    ) {
            citiesListViewModel
                .cityListAction
                .postValue(CityListAction.SearchCityList(newText))

    }
}
