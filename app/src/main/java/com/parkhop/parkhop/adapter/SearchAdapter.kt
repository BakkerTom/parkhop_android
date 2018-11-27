package com.parkhop.parkhop.adapter

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.parkhop.parkhop.R
import com.parkhop.parkhop.fragment.BottomSheetFragment
import com.parkhop.parkhop.inflate
import com.parkhop.parkhop.model.Ride
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.journal_item.view.*

class SearchAdapter(fragmentManager: FragmentManager, rides: ArrayList<Ride>) : RecyclerView.Adapter<SearchAdapter.RideHolder>() {
    private val rides = rides
    private val fragmentManager = fragmentManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideHolder {
        val inflatedView = parent.inflate(R.layout.journal_item, false)
        return RideHolder(inflatedView, fragmentManager)
    }

    override fun onBindViewHolder(holder: RideHolder, position: Int) {
        val ride = rides[position]
        holder.bindRide(ride)
    }

    override fun getItemCount() = rides.count()

    fun updateData(data: ArrayList<Ride>) {
        rides.clear()
        rides.addAll(data)
        notifyDataSetChanged()
    }

    class RideHolder(v: View, fragmentManager: FragmentManager) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var ride: Ride? = null
        private var fragmentManager: FragmentManager = fragmentManager

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (ride != null) {
                val bottomSheetFragment = BottomSheetFragment.new(ride!!)
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }

        fun bindRide(ride: Ride) {
            this.ride = ride
            update()
        }

        private fun update() {
            view.nameView.text = ride!!.name
            view.parkView.text = ride!!.park.name
            Picasso.get().load("https://programmerenissexy.nl" + ride!!.thumbnailUrl).into(view.thumbnailView)
        }

        companion object {
            private val RIDE_KEY = "RIDE"
        }

    }
}