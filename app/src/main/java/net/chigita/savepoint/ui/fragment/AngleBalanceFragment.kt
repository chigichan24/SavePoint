package net.chigita.savepoint.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.FragmentAngleBalanceBinding
import net.chigita.savepoint.di.Injectable
import net.chigita.savepoint.util.changed
import net.chigita.savepoint.viewmodel.AngleViewModel
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-27.
 */
class AngleBalanceFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentAngleBalanceBinding
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var angleViewModel: AngleViewModel
  private val args by navArgs<AngleBalanceFragmentArgs>()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_angle_balance,
        container,
        false
    )
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val args = AngleBalanceFragmentArgs.fromBundle(arguments!!)
    angleViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(AngleViewModel::class.java)
    angleViewModel.angleLiveData.changed(viewLifecycleOwner) {
      binding.targetX.text = it.x.toString()
      binding.targetY.text = it.y.toString()
    }
    angleViewModel.loadAngle(args.angleUuid)
  }
}