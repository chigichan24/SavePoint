package net.chigita.savepoint.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
}