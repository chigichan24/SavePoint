package net.chigita.savepoint.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import net.chigita.savepoint.vo.Thing

/**
 * Created by chigichan24 on 2019-05-24.
 */
@Dao
interface ThingDao {
  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun insert(thing: Thing)

  @Update
  suspend fun update(thing: Thing)

  @Query("SELECT * FROM thing")
  suspend fun findAll(): List<Thing>

  @Transaction
  @Query("SELECT * FROM thing")
  suspend fun findThingAndAngles(): List<ThingAndAngles>

}