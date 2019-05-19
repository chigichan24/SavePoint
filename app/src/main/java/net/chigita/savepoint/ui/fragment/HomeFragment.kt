package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import dagger.multibindings.IntoMap
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentHomeBinding
import net.chigita.savepoint.di.ViewModelKey
import net.chigita.savepoint.viewmodel.HomeViewModel

/**
 * Created by chigichan24 on 2019-05-14.
 */
class HomeFragment : DaggerFragment() {
  private lateinit var binding: FragmentHomeBinding
  //@Inject lateinit var viewModelFactory: ViewModelFactory

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_home,
        container,
        false
    )
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    // val viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    //binding.viewModel = viewModel
  }
}

@Module
abstract class HomeFragmentModule {
  @Module
  companion object {
    @JvmStatic
    @Provides
    fun provideLifecycle(
        homeFragment: HomeFragment
    ): Lifecycle {
      return homeFragment.viewLifecycleOwner.lifecycle
    }
  }

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  internal abstract fun bindMainViewModel(viewModel: HomeViewModel): ViewModel
}