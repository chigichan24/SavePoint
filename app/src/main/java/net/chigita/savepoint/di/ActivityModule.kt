package net.chigita.savepoint.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import net.chigita.savepoint.R
import net.chigita.savepoint.ui.activity.MainActivity
import net.chigita.savepoint.ui.fragment.HomeFragment
import net.chigita.savepoint.ui.fragment.HomeFragmentModule
import net.chigita.savepoint.ui.fragment.RegisterFragment
import net.chigita.savepoint.ui.fragment.RegisterFragmentModule

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

  @ContributesAndroidInjector(
      modules = [RegisterFragmentModule::class]
  )
  abstract fun contriubteRegisterFragment(): RegisterFragment

  @Module
  companion object {
    @Provides
    @JvmStatic
    fun provideNavController(mainActivity: MainActivity): NavController {
      return Navigation.findNavController(mainActivity, R.id.nav_host_fragment)
    }
  }

  @Module
  abstract class MainActivityBuilder {
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
  }
}