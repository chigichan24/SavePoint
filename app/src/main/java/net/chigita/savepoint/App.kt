package net.chigita.savepoint

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.chigita.savepoint.di.AppInjector
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-12.
 */
class App : Application(), HasActivityInjector {
  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    AppInjector.init(this)
  }

  override fun activityInjector() = dispatchingAndroidInjector
}