package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentHomeBinding
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-14.
 */
class HomeFragment : DaggerFragment() {
  private lateinit var binding: FragmentHomeBinding
  @Inject lateinit var navController: NavController

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
    binding.fab.setOnClickListener {
      navController.navigate(R.id.action_main_to_register)
    }
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