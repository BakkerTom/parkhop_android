package com.parkhop.parkhop.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.parkhop.parkhop.R
import com.parkhop.parkhop.inflate
import com.parkhop.parkhop.model.JournalEntry
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.journal_item.view.*

class JournalAdapter(private val journalEntries: List<JournalEntry>) : RecyclerView.Adapter<JournalAdapter.JournalEntryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalEntryHolder {
        val inflatedView = parent.inflate(R.layout.journal_item, false)
        return JournalEntryHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: JournalEntryHolder, position: Int) {
        val journalEntry = journalEntries[position]
        holder.bindJournalEntry(journalEntry)
    }

    override fun getItemCount() = journalEntries.size

    class JournalEntryHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var journalEntry: JournalEntry? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindJournalEntry(journalEntry: JournalEntry) {
            this.journalEntry = journalEntry
            view.nameView.text = journalEntry.rideName
            view.parkView.text = journalEntry.parkName
            Picasso.get().load("https://programmerenissexy.nl" + journalEntry.thumbnailUrl).into(view.thumbnailView)
        }

        companion object {
            private val JOURNAL_ENTRY_KEY = "JOURNAL_ENTRY"
        }
    }
}
