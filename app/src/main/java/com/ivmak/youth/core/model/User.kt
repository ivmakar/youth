package com.ivmak.youth.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
        @PrimaryKey
        var name: String
    )