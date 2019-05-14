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
import net.chigita.savepoint.databinding.FragmentRegisterBinding

/**
 * Created by chigichan24 on 2019-05-14.
 */
class RegisterFragment : DaggerFragment() {
  private lateinit var binding: FragmentRegisterBinding

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_register,
        container,
        false
    )
    return binding.root
  }
}

@Module
abstract class RegisterFragmentModule {
  @Module
  companion object {
    @JvmStatic
    @Provides
    fun provideLifecycle(
        registerFragment: RegisterFragment
    ): Lifecycle {
      return registerFragment.viewLifecycleOwner.lifecycle
    }
  }
}