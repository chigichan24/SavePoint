package net.chigita.savepoint.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Created by chigichan24 on 2019-05-23.
 */
@Entity
data class Thing(
    @PrimaryKey val uuid: String,
    val name: String
) {
  companion object {
    fun new(name: String) = Thing(UUID.randomUUID().toString(), name)
  }
}