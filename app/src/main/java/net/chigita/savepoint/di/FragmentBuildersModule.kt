package net.chigita.savepoint.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chigita.savepoint.ui.fragment.HomeFragment
import net.chigita.savepoint.ui.fragment.RegisterFragment

/**
 * Created by chigichan24 on 2019-05-19.
 */
@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contribubteHomeFragment(): HomeFragment

  @ContributesAndroidInjector
  abstract fun contribubteRegisterFragment(): RegisterFragment
}