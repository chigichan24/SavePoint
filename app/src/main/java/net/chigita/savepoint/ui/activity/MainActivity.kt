package net.chigita.savepoint.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.ActivityMainBinding
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-12.
 */

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
  }

  override fun supportFragmentInjector() = dispatchingAndroidInjector
}