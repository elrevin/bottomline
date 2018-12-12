package me.elrevin.bottomline.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey
        var id: Int?,
        var pinCode: String?
)