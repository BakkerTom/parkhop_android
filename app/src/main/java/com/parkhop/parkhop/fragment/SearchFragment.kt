package com.parkhop.parkhop.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.parkhop.parkhop.R
import com.parkhop.parkhop.adapter.SearchAdapter
import com.parkhop.parkhop.model.Ride


class SearchFragment : Fragment() {
    private var rides = ArrayList<Ride>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, null)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager

        adapter = SearchAdapter(activity!!.supportFragmentManager, rides)
        recyclerView.adapter = adapter

        getRides()

        setHasOptionsMenu(true)

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)

        val searchItem = menu?.findItem(R.id.menu_search)

        var searchView: SearchView? = null

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView

        }

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(searchTerm: String?): Boolean {
                searchRides(searchTerm)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun searchRides(searchTerm: String?) {
        if (searchTerm != null) {
            Ride.search(searchTerm) { results -> adapter.updateData(results) }
        }
    }

    private fun getRides() {
        Ride.popular { results -> adapter.updateData(results) }
    }
}