package net.chigita.savepoint.ui.fragment

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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
class AngleRegisterFragment : Fragment(), Injectable, SensorEventListener {
  private lateinit var binding: FragmentAngleRegisterBinding
  private val args by navArgs<AngleRegisterFragmentArgs>()
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var angleViewModel: AngleViewModel

  private var mutableGeoMagneticList: MutableList<Float> = listOf(0.0f, 0.0f, 0.0f).toMutableList()
  private var mutableAccelerationList: MutableList<Float> = listOf(0.0f, 0.0f, 0.0f).toMutableList()
  private var x: Double = 0.0
  private var y: Double = 0.0
  private var z: Double = 0.0

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

    // TODO: Fix me :(
    // About sensor setup
    val sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    sensorManager.registerListener(this, accelerationSensor, SensorManager.SENSOR_DELAY_UI)
    sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_UI)

    val args = AngleRegisterFragmentArgs.fromBundle(arguments!!)
    binding.rootConstraint.setOnTouchListener { v, _ ->
      v.requestFocus()
    }
    angleViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(AngleViewModel::class.java)
    binding.measureButton.setOnClickListener {
      binding.lottieAnim.visibility = View.VISIBLE
      binding.lottieAnim.playAnimation()
      updateOrientations()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_fragment_register, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_completed) {
      angleViewModel.registerAngle(
          args.thingUuid,
          binding.thingEditText.text.toString(),
          binding.angleDescriptionEditText.text.toString(),
          x,
          y,
          z
      )
      navigateToThingRegisterPage()
      return false
    }
    return true
  }

  fun navigateToThingRegisterPage() = findNavController().popBackStack()

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

  override fun onSensorChanged(event: SensorEvent?) {
    when (event?.sensor?.type) {
      Sensor.TYPE_MAGNETIC_FIELD -> {
        mutableGeoMagneticList = event.values.clone().toMutableList()
      }
      Sensor.TYPE_ACCELEROMETER -> {
        mutableAccelerationList = event.values.clone().toMutableList()
      }
    }
  }

  private fun updateOrientations() {
    val R = FloatArray(MATRIX_SIZE)
    val I = FloatArray(MATRIX_SIZE)
    val O = FloatArray(MATRIX_SIZE)
    val orientation = FloatArray(MATRIX_SIZE)
    SensorManager.getRotationMatrix(
        R,
        I,
        mutableAccelerationList.toFloatArray(),
        mutableGeoMagneticList.toFloatArray()
    )
    SensorManager.remapCoordinateSystem(
        R,
        SensorManager.AXIS_X,
        SensorManager.AXIS_Z,
        O
    )
    SensorManager.getOrientation(O, orientation)
    x = Math.floor(Math.toDegrees(orientation[0].toDouble()))
    y = Math.floor(Math.toDegrees(orientation[1].toDouble()))
    z = Math.floor(Math.toDegrees(orientation[2].toDouble()))
  }

  companion object {
    private const val MATRIX_SIZE = 3 * 3
  }
}