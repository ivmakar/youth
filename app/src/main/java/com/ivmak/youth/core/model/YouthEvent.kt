package com.ivmak.youth.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
class YouthEvent(
    @PrimaryKey
    var timestamp: Long,
    var name: String,
    var description: String,
    var members: MutableList<User>
)