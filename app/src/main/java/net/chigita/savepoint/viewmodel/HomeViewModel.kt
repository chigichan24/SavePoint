package net.chigita.savepoint.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.chigita.savepoint.repository.ThingRepository
import net.chigita.savepoint.vo.Thing
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-15.
 */
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val repository: ThingRepository
) : AndroidViewModel(app) {

  private val mutableThingsLiveData = MutableLiveData<List<Thing>>()
  val thingsLivaData: LiveData<List<Thing>>
    get() = mutableThingsLiveData
  val things: List<Thing>?
    get() = mutableThingsLiveData.value

  fun clickFab() {
    viewModelScope.launch {
      try {
        val things = repository.loadthings()
        mutableThingsLiveData.value = things
        // TODO: Remove
        /*
        val dummy: List<Thing> = listOf(
            Thing(UUID.randomUUID().toString(), "ピコ太郎"),
            Thing(UUID.randomUUID().toString(), "マイクロ太郎"),
            Thing(UUID.randomUUID().toString(), "ナノ太郎")
        )
        mutableThingsLiveData.value = dummy
        */
      } catch (e: Exception) {
        onError(app.applicationContext, e)
      }
    }
  }
}