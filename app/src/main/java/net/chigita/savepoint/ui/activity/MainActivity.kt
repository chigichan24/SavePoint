package net.chigita.savepoint.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.android.support.DaggerAppCompatActivity
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.ActivityMainBinding

/**
 * Created by chigichan24 on 2019-05-12.
 */

class MainActivity : DaggerAppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
  }
}