package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentHomeBinding

/**
 * Created by chigichan24 on 2019-05-14.
 */
class HomeFragment : DaggerFragment() {
  private lateinit var binding: FragmentHomeBinding

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
}