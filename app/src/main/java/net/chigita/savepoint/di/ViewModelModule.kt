package net.chigita.savepoint.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.chigita.savepoint.viewmodel.ThingViewModel

/**
 * Created by chigichan24 on 2019-05-16.
 */
@Module
abstract class ViewModelModule {
  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(ThingViewModel::class)
  abstract fun bindHomeViewModel(viewModel: ThingViewModel): ViewModel
}