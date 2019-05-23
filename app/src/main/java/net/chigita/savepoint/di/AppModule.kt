package net.chigita.savepoint.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.chigita.savepoint.db.AngleDao
import net.chigita.savepoint.db.SavePointDb
import net.chigita.savepoint.db.ThingDao
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-05-20.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {
  @Singleton
  @Provides
  fun provideDb(app: Application): SavePointDb {
    return Room
        .databaseBuilder(app, SavePointDb::class.java, "savepoint.db")
        .fallbackToDestructiveMigration()
        .build()
  }

  @Singleton
  @Provides
  fun provideThingDao(db: SavePointDb): ThingDao {
    return db.thingDao()
  }

  @Singleton
  @Provides
  fun provideAngleDao(db: SavePointDb): AngleDao {
    return db.angleDao()
  }
}