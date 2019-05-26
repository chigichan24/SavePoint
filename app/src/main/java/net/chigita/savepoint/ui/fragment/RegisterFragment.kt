package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentRegisterBinding
import net.chigita.savepoint.di.Injectable
import net.chigita.savepoint.util.changed
import net.chigita.savepoint.viewmodel.ThingViewModel
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-14.
 */
class RegisterFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentRegisterBinding
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var thingViewModel: ThingViewModel
  private val args by navArgs<RegisterFragmentArgs>()

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
    thingViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(ThingViewModel::class.java)
    thingViewModel.thingLiveData.changed(viewLifecycleOwner) {
      binding.thingEditText.setText(it.name)
    }
    binding.fab.setOnClickListener {
      navigateToAngleRegister()
    }
    val args = RegisterFragmentArgs.fromBundle(arguments!!)
    args.uuid?.let {
      thingViewModel.loadThing(it)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_fragment_register, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_completed) {
      if (args.uuid == null) {
        thingViewModel.registerThing(binding.thingEditText.text.toString())
        navigateToHome()
      }
      return false
    }
    return true
  }

  fun navigateToAngleRegister() = findNavController().navigate(
      R.id.action_register_to_angle_register)

  fun navigateToHome() = findNavController().popBackStack()
}
