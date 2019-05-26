package net.chigita.savepoint.vo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Created by chigichan24 on 2019-05-24.
 */
@Entity(
    foreignKeys = [
      ForeignKey(
          entity = Thing::class,
          parentColumns = ["uuid"],
          childColumns = ["thingUuid"],
          onDelete = ForeignKey.CASCADE
      )
    ]
)
data class Angle(
    @PrimaryKey val uuid: String,
    val thingUuid: String,
    val name: String,
    val description: String?,
    val x: Double,
    val y: Double,
    val z: Double
) {
  companion object {
    fun new(
        thingUuid: String,
        name: String,
        description: String?,
        x: Double,
        y: Double,
        z: Double
    ): Angle {
      if (name.isEmpty()) {
        throw IllegalStateException("cannot allow blank name")
      }
      return Angle(
          UUID.randomUUID().toString(),
          thingUuid,
          name,
          description ?: "",
          x,
          y,
          z
      )
    }
  }
}