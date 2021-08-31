package com.samy.cityselector

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment


class CityListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_city_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
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
