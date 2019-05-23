package net.chigita.savepoint.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import net.chigita.savepoint.vo.Angle

/**
 * Created by chigichan24 on 2019-05-24.
 */
@Dao
interface AngleDao {
  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun insert(angle: Angle)

  @Update
  suspend fun update(angle: Angle)
}