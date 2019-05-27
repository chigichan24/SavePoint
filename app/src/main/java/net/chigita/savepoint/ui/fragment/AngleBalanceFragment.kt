package net.chigita.savepoint.ui.fragment

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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
class AngleBalanceFragment : Fragment(), Injectable, SensorEventListener {
  private lateinit var binding: FragmentAngleBalanceBinding
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var angleViewModel: AngleViewModel
  private val args by navArgs<AngleBalanceFragmentArgs>()

  private var mutableGeoMagneticList: MutableList<Float> = listOf(0.0f, 0.0f, 0.0f).toMutableList()
  private var mutableAccelerationList: MutableList<Float> = listOf(0.0f, 0.0f, 0.0f).toMutableList()
  private var x: Double = 0.0
  private var y: Double = 0.0

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

    // TODO: Fix me :(
    // About sensor setup
    val sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    sensorManager.registerListener(this, accelerationSensor, SensorManager.SENSOR_DELAY_UI)
    sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_UI)

    val args = AngleBalanceFragmentArgs.fromBundle(arguments!!)
    angleViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(AngleViewModel::class.java)
    angleViewModel.angleLiveData.changed(viewLifecycleOwner) {
      binding.targetX.text = it.x.toString()
      binding.targetY.text = it.y.toString()
    }
    angleViewModel.loadAngle(args.angleUuid)
  }

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
    updateOrientations()
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

    binding.currentX.text = x.toString()
    binding.currentY.text = y.toString()

  }

  companion object {
    private const val MATRIX_SIZE = 3 * 3
  }
}