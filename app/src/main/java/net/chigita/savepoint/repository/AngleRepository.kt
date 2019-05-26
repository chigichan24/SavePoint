package net.chigita.savepoint.repository

import net.chigita.savepoint.db.AngleDao
import net.chigita.savepoint.vo.Angle
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-05-27.
 */
@Singleton
class AngleRepository @Inject constructor(
    private val angleDao: AngleDao
) {
  suspend fun insert(angle: Angle) {
    return angleDao.insert(angle)
  }
}