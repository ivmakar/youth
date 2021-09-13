package com.ivmak.youth.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @PrimaryKey
    var name: String
)