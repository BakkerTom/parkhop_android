package com.parkhop.parkhop.model

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.parkhop.parkhop.ParkhopApplication
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

class Ride: Serializable {
    val id: Int
    val name: String
    val park: Park
    val thumbnailUrl: String

    constructor(jsonObject: JSONObject) {
        this.id = jsonObject.getInt("id")
        this.name = jsonObject.getString("name")
        this.park = Park(jsonObject.getJSONObject("park"))
        this.thumbnailUrl = jsonObject.getString("thumbnail_url")
    }

    constructor(id: Int, name: String, park: Park, thumbnailUrl: String) {
        this.id = id
        this.name = name
        this.park = park
        this.thumbnailUrl = thumbnailUrl
    }

    companion object {
        fun popular(callback: (result: ArrayList<Ride>) -> Unit) {
            val queue = ParkhopApplication.getShared().requestQueue

            val request = JsonArrayRequest("https://programmerenissexy.nl/api/rides", Response.Listener<JSONArray> { jsonArray ->
                val list = ArrayList<Ride>()
                for (i in 0..(jsonArray.length() - 1)) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    list.add(Ride(jsonObject))
                }
                callback(list)
            }, Response.ErrorListener { error ->
                Log.e("Something went wrong", error.localizedMessage)
            })

            queue.add(request)
        }

        fun search(searchTerm: String, callback: (result: ArrayList<Ride>) -> Unit) {
            val queue = ParkhopApplication.getShared().requestQueue

            val request = JsonArrayRequest("https://programmerenissexy.nl/api/rides?search=$searchTerm", Response.Listener<JSONArray> { jsonArray ->
                val list = ArrayList<Ride>()
                for (i in 0..(jsonArray.length() - 1)) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    list.add(Ride(jsonObject))
                }
                callback(list)
            }, Response.ErrorListener { error ->
                Log.e("Something went wrong", error.localizedMessage)
            })

            queue.add(request)
        }
    }
}