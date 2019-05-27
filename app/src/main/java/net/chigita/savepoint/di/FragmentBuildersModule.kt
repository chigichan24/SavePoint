package net.chigita.savepoint.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chigita.savepoint.ui.fragment.AngleBalanceFragment
import net.chigita.savepoint.ui.fragment.AngleRegisterFragment
import net.chigita.savepoint.ui.fragment.AngleSetUpFragment
import net.chigita.savepoint.ui.fragment.HomeFragment
import net.chigita.savepoint.ui.fragment.LicenseFragment
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

  @ContributesAndroidInjector
  abstract fun contributeAngleRegisterFragment(): AngleRegisterFragment

  @ContributesAndroidInjector
  abstract fun contriubteSetUpAngleFragment(): AngleSetUpFragment

  @ContributesAndroidInjector
  abstract fun contriuteAngleBalanceFragment(): AngleBalanceFragment

  @ContributesAndroidInjector
  abstract fun contribbuteLicenseFragment(): LicenseFragment
}