package com.parkhop.parkhop.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class JournalEntry: RealmObject() {
    var date: Date = Date()
    var rideName: String = ""
    var parkName: String = ""
    var thumbnailUrl: String = ""
//    var park: Park? = null
}