package com.parkhop.parkhop.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.ThemedSpinnerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parkhop.parkhop.R
import com.parkhop.parkhop.adapter.JournalAdapter
import com.parkhop.parkhop.model.JournalEntry
import com.vicpin.krealmextensions.queryAll

class JournalFragment : Fragment() {
    private var entries = JournalEntry().queryAll()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: JournalAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_journal, null)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager

        adapter = JournalAdapter(entries)
        recyclerView.adapter = adapter

        return rootView
    }
}