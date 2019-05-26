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
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentRegisterBinding
import net.chigita.savepoint.di.Injectable
import net.chigita.savepoint.ui.adapter.AnglesSection
import net.chigita.savepoint.util.changed
import net.chigita.savepoint.viewmodel.AngleViewModel
import net.chigita.savepoint.viewmodel.ThingViewModel
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-14.
 */
class RegisterFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentRegisterBinding
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var thingViewModel: ThingViewModel
  lateinit var angleViewModel: AngleViewModel
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
    val args = RegisterFragmentArgs.fromBundle(arguments!!)
    binding.rootConstraint.setOnTouchListener { v, _ ->
      v.requestFocus()
    }
    thingViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(ThingViewModel::class.java)
    angleViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(AngleViewModel::class.java)
    thingViewModel.thingLiveData.changed(viewLifecycleOwner) {
      binding.thingEditText.setText(it.name)
    }
    angleViewModel.registedAnglesLiveData.changed(viewLifecycleOwner) {
      binding.recyclerView.adapter = GroupAdapter<ViewHolder>().apply {
        add(AnglesSection(it, viewLifecycleOwner))
      }
    }
    binding.fab.setOnClickListener {
      (args.uuid)?.let {
        navigateToAngleRegister(it)
      }
    }
    (args.uuid)?.let {
      thingViewModel.loadThing(it)
      angleViewModel.loadRegistedAngles(it)
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

  fun navigateToAngleRegister(uuid: String) {
    val bundle = AngleRegisterFragmentArgs.Builder()
        .setThingUuid(uuid)
        .build()
        .toBundle()
    findNavController().navigate(R.id.action_register_to_angle_register, bundle)
  }

  fun navigateToHome() = findNavController().popBackStack()
}
