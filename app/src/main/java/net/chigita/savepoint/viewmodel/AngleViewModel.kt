package net.chigita.savepoint.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.chigita.savepoint.repository.AngleRepository
import net.chigita.savepoint.vo.Angle
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-27.
 */
class AngleViewModel @Inject constructor(
    private val app: Application,
    private val repository: AngleRepository
) : AndroidViewModel(app) {

  private val mutableRegisterdAnglesLiveData = MutableLiveData<List<Angle>>()
  val registedAnglesLiveData: LiveData<List<Angle>>
    get() = mutableRegisterdAnglesLiveData
  val registerdAngles: List<Angle>
    get() = mutableRegisterdAnglesLiveData.value ?: emptyList()

  private val mutableAngleLiveData = MutableLiveData<Angle>()
  val angleLiveData: LiveData<Angle>
    get() = mutableAngleLiveData
  val angle: Angle?
    get() = mutableAngleLiveData.value

  fun registerAngle(
      thingUuid: String,
      name: String,
      description: String?,
      x: Double,
      y: Double,
      z: Double
  ) {
    viewModelScope.launch {
      try {
        val angle = Angle.new(
            thingUuid,
            name,
            description,
            x,
            y,
            z
        )
        repository.insert(angle)
      } catch (e: Exception) {
        onError(app.applicationContext, e)
      }
    }
  }

  fun loadAngle(uuid: String) {
    viewModelScope.launch {
      try {
        val angle = repository.load(uuid)
        mutableAngleLiveData.value = angle
      } catch (e: Exception) {
        onError(app.applicationContext, e)
      }
    }
  }


  fun loadRegistedAngles(thingUuid: String) {
    viewModelScope.launch {
      try {
        val angles = repository.loadAngles(thingUuid)
        mutableRegisterdAnglesLiveData.value = angles
      } catch (e: Exception){
        onError(app.applicationContext, e)
      }
    }
  }
}