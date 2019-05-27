package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentSetUpAngleBinding
import net.chigita.savepoint.di.Injectable

/**
 * Created by chigichan24 on 2019-05-27.
 */
class AngleSetUpFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentSetUpAngleBinding
  private val args by navArgs<AngleRegisterFragmentArgs>()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_set_up_angle,
        container,
        false
    )
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val args = AngleSetUpFragmentArgs.fromBundle(arguments!!)
  }
}