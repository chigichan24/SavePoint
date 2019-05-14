package net.chigita.savepoint.di

import androidx.fragment.app.FragmentActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chigita.savepoint.ui.activity.MainActivity
import net.chigita.savepoint.ui.fragment.HomeFragment
import net.chigita.savepoint.ui.fragment.HomeFragmentModule

/**
 * Created by chigichan24 on 2019-05-12.
 */

@Module
abstract class ActivityModule {

  @Binds
  abstract fun providesActivity(mainActivity: MainActivity): FragmentActivity

  @ContributesAndroidInjector(
      modules = [HomeFragmentModule::class]
  )
  abstract fun contributeHomeFragment(): HomeFragment

  @Module
  abstract class MainActivityBuilder {
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
  }
}