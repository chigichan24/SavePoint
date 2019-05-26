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
class ThingViewModel @Inject constructor(
    private val app: Application,
    private val repository: ThingRepository
) : AndroidViewModel(app) {

  private val mutableThingsLiveData = MutableLiveData<List<Thing>>()
  val thingsLivaData: LiveData<List<Thing>>
    get() {
      loadThings()
      return mutableThingsLiveData
    }
  val things: List<Thing>?
    get() = mutableThingsLiveData.value

  private val mutableThingLiveData = MutableLiveData<Thing>()
  val thingLiveData: LiveData<Thing>
      get() = mutableThingLiveData
  val thing: Thing?
    get() = mutableThingLiveData.value

  fun registerThing(name: String) {
    viewModelScope.launch {
      try {
        val thing = Thing.new(name)
        repository.insert(thing)
      } catch (e: Exception) {
        onError(app.applicationContext, e)
      }
    }
  }

  fun loadThing(uuid: String){
    viewModelScope.launch {
      try{
        val thing = repository.load(uuid)
        thing?.let {
          mutableThingLiveData.value = it
        }
      } catch (e: Exception) {
        onError(app.applicationContext, e)
      }
    }
  }

  private fun loadThings() {
    viewModelScope.launch {
      try {
        val things = repository.load()
        mutableThingsLiveData.value = things
      } catch (e: Exception) {
        onError(app.applicationContext, e)
      }
    }
  }

}