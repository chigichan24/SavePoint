package net.chigita.savepoint.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by chigichan24 on 2019-05-23.
 */
@Entity
data class Thing(
    @PrimaryKey val uuid: String,
    val name: String
)