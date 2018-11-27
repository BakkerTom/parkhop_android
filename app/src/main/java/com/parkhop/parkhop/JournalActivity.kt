//package com.parkhop.parkhop
//
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.SearchView
//import android.view.Menu
//import com.parkhop.parkhop.model.JournalEntry
//import com.parkhop.parkhop.model.Ride
//import kotlinx.android.synthetic.main.activity_main.*
//import java.util.*
//
//class JournalActivity : AppCompatActivity() {
//    private var journalEntries: ArrayList<JournalEntry> = ArrayList()
//
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    private lateinit var adapter: JournalAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        getRides()
//
//        linearLayoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = linearLayoutManager
//
//        adapter = JournalAdapter(journalEntries)
//        recyclerView.adapter = adapter
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        val searchItem = menu?.findItem(R.id.menu_search)
//
//        var searchView: SearchView? = null
//        if (searchItem != null) {
//            searchView = searchItem.actionView as SearchView
//
//        }
//
//        if (searchView != null) {
//            // Something here
//        }
//
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    private fun getRides() {
//        Ride.search("Test") { response ->
//            response.forEach { ride ->
//                journalEntries.add(JournalEntry(Date(), ride))
//                adapter = JournalAdapter(journalEntries)
//                recyclerView.adapter = adapter
//            }
//        }
//    }
//}
