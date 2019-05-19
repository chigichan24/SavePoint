package net.chigita.savepoint.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chigita.savepoint.ui.activity.MainActivity

/**
 * Created by chigichan24 on 2019-05-12.
 */

@Module
abstract class MainActivityModule {

  @ContributesAndroidInjector(
      modules = [FragmentBuildersModule::class]
  )
  abstract fun contributeMainActivity(): MainActivity
}