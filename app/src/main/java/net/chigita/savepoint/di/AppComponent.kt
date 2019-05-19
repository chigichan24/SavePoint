package net.chigita.savepoint.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import net.chigita.savepoint.App
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-05-12.
 */

@Singleton
@Component(modules = [
  AndroidInjectionModule::class,
  AppModule::class,
  MainActivityModule::class
])
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: App)
}
