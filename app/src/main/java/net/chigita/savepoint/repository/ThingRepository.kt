package net.chigita.savepoint.repository

import net.chigita.savepoint.db.ThingDao
import net.chigita.savepoint.vo.Thing
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-05-24.
 */
@Singleton
class ThingRepository @Inject constructor(
  private val thingDao: ThingDao
) {
    suspend fun loadthings(): List<Thing> {
      return thingDao.findAll()
    }
}