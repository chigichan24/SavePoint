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
  suspend fun load(): List<Thing> {
    return thingDao.findAll()
  }

  suspend fun load(uuid: String): Thing? {
    return thingDao.find(uuid)
  }

  suspend fun insert(thing: Thing) {
    return thingDao.insert(thing)
  }
}