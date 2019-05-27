package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentLicenseBinding
import net.chigita.savepoint.di.Injectable

/**
 * Created by chigichan24 on 2019-05-28.
 */
class LicenseFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentLicenseBinding

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_license,
        container,
            false
    )
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    binding.webView.loadUrl("file:///android_asset/licenses.html")
  }

}