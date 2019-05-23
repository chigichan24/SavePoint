package net.chigita.savepoint.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.chigita.savepoint.vo.Angle
import net.chigita.savepoint.vo.Thing

/**
 * Created by chigichan24 on 2019-05-24.
 */
@Database(
    entities = [
      Angle::class,
      Thing::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SavePointDb : RoomDatabase() {
  abstract fun thingDao(): ThingDao

  abstract fun angleDao(): AngleDao
}