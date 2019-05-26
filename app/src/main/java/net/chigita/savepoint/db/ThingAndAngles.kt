package net.chigita.savepoint.db

import androidx.room.Embedded
import androidx.room.Relation
import net.chigita.savepoint.vo.Angle
import net.chigita.savepoint.vo.Thing

/**
 * Created by chigichan24 on 2019-05-24.
 */

class ThingAndAngles {
  @Embedded
  lateinit var thing: Thing

  @Relation(parentColumn = "uuid", entityColumn = "thingUuid")
  lateinit var angles: List<Angle>
}