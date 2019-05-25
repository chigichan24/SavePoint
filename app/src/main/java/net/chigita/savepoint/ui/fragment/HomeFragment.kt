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
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentHomeBinding
import net.chigita.savepoint.di.Injectable
import net.chigita.savepoint.ui.adapter.ThingsSection
import net.chigita.savepoint.util.changed
import net.chigita.savepoint.viewmodel.ThingViewModel
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-14.
 */
class HomeFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentHomeBinding
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var thingViewModel: ThingViewModel

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_home,
        container,
        false
    )
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    thingViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(ThingViewModel::class.java)
    binding.viewModel = thingViewModel

    binding.fab.setOnClickListener {
      navigateToRegister()
    }

    thingViewModel.thingsLivaData.changed(viewLifecycleOwner) {
      binding.recyclerView.adapter = GroupAdapter<ViewHolder>().apply {
        add(ThingsSection(it, viewLifecycleOwner))
      }
    }
  }

  fun navigateToRegister() = findNavController().navigate(R.id.action_main_to_register)
}