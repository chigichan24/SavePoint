package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentRegisterBinding
import net.chigita.savepoint.di.Injectable

/**
 * Created by chigichan24 on 2019-05-14.
 */
class RegisterFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentRegisterBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

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

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    binding.rootConstraint.setOnTouchListener { v, _ ->
      v.requestFocus()
    }
    binding.fab.setOnClickListener {
      navigateToAngleRegister()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_fragment_register, menu)
  }

  fun navigateToAngleRegister() = findNavController().navigate(R.id.action_register_to_angle_register)
}
