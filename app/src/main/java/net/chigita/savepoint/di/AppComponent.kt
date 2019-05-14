package net.chigita.savepoint.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.chigita.savepoint.App
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-05-12.
 */

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  ActivityModule.MainActivityBuilder::class,
  AppModule::class
])
interface AppComponent : AndroidInjector<App> {
  @Component.Builder
  interface Builder {
    @BindsInstance fun application(application: Application): Builder

    fun build(): AppComponent
  }

  override fun inject(app: App)
}

fun Application.createAppComponent() = DaggerAppComponent.builder()
    .application(this)
    .build()