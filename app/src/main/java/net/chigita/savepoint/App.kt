package net.chigita.savepoint

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.chigita.savepoint.di.createAppComponent

/**
 * Created by chigichan24 on 2019-05-12.
 */
class App : DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return createAppComponent()
  }
}