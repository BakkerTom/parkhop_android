package com.parkhop.parkhop.model

import org.json.JSONObject
import java.io.Serializable

class Park: Serializable {
    val id: Int
    val name: String

    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }

    constructor(jsonObject: JSONObject) {
        this.id = jsonObject.getInt("id")
        this.name = jsonObject.getString("name")
    }

}