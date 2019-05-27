package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
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
import net.chigita.savepoint.databinding.FragmentSetUpAngleBinding
import net.chigita.savepoint.di.Injectable
import net.chigita.savepoint.ui.adapter.AnglesSection
import net.chigita.savepoint.util.changed
import net.chigita.savepoint.viewmodel.AngleViewModel
import net.chigita.savepoint.viewmodel.ThingViewModel
import net.chigita.savepoint.vo.Angle
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-27.
 */
class AngleSetUpFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentSetUpAngleBinding
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var thingViewModel: ThingViewModel
  lateinit var angleViewModel: AngleViewModel
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
    thingViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(ThingViewModel::class.java)
    angleViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(AngleViewModel::class.java)
    thingViewModel.thingLiveData.changed(viewLifecycleOwner) {
      binding.thingText.text = it.name
    }
    angleViewModel.registedAnglesLiveData.changed(viewLifecycleOwner) {
      binding.recyclerView.adapter = GroupAdapter<ViewHolder>().apply {
        add(AnglesSection(it, viewLifecycleOwner) {navigateToHome(it)})
      }
    }
    args.thingUuid.apply {
      thingViewModel.loadThing(this)
      angleViewModel.loadRegistedAngles(this)
    }
  }

  fun navigateToHome(angle: Angle) = findNavController().navigate(R.id.action_angle_set_up_to_home)
}