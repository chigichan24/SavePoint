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
import net.chigita.savepoint.databinding.FragmentAngleRegisterBinding
import net.chigita.savepoint.di.Injectable
import net.chigita.savepoint.viewmodel.AngleViewModel
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-25.
 */
class AngleRegisterFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentAngleRegisterBinding
  private val args by navArgs<AngleRegisterFragmentArgs>()
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var angleViewModel: AngleViewModel

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
        R.layout.fragment_angle_register,
        container,
        false
    )
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val args = AngleRegisterFragmentArgs.fromBundle(arguments!!)
    binding.rootConstraint.setOnTouchListener { v, _ ->
      v.requestFocus()
    }
    angleViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(AngleViewModel::class.java)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_fragment_register, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_completed) {
      (args.thingUuid)?.let {
        angleViewModel.registerAngle(
            it,
            binding.thingEditText.text.toString(),
            binding.angleDescriptionEditText.text.toString(),
            1.0,
            1.0,
            1.0
        )
        navigateToThingRegisterPage()
      }
      return false
    }
    return true
  }

  fun navigateToThingRegisterPage() = findNavController().popBackStack()
}