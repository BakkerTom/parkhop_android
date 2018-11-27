package com.parkhop.parkhop.fragment

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.parkhop.parkhop.R
import com.parkhop.parkhop.model.JournalEntry
import com.parkhop.parkhop.model.Ride
import com.squareup.picasso.Picasso
import com.vicpin.krealmextensions.save
import io.realm.Realm
import java.util.*

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var ride: Ride

    private lateinit var rideTextView: TextView
    private lateinit var parkTextView: TextView
    private lateinit var rideImageView: ImageView
    private lateinit var addToJournalButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
        ride = arguments!!.getSerializable(DESCRIBABLE_KEY) as Ride

        rideTextView = rootView.findViewById(R.id.rideTextView)
        parkTextView = rootView.findViewById(R.id.parkTextView)
        rideImageView = rootView.findViewById(R.id.rideImageView)
        addToJournalButton = rootView.findViewById(R.id.addToJournalButton)

        addToJournalButton.setOnClickListener(onClickListener)

        bindRide()

        return rootView
    }

    private val onClickListener = View.OnClickListener {
        var entry = JournalEntry()
        entry.date = Date()
        entry.rideName = ride.name
        entry.parkName = ride.park.name
        entry.thumbnailUrl = ride.thumbnailUrl

        val realm = Realm.getDefaultInstance()
        realm.use { realm ->
            realm.executeTransaction { realm -> realm.copyToRealm(entry) }
            this.dismiss()
        }
    }

    private fun bindRide() {
        rideTextView.text = ride.name
        parkTextView.text = ride.park.name

        Picasso.get().load("https://programmerenissexy.nl" + ride.thumbnailUrl).into(rideImageView)
    }

    companion object {
        const val DESCRIBABLE_KEY = "RIDE"

        fun new(ride: Ride) : BottomSheetFragment {
            var fragment = BottomSheetFragment()
            val bundle = Bundle()
            bundle.putSerializable(DESCRIBABLE_KEY, ride)
            fragment.arguments = bundle
            return fragment
        }
    }
}