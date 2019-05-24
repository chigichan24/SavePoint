package net.chigita.savepoint.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

/**
 * Created by chigichan24 on 2019-05-25.
 */

fun <T> LiveData<T>.distinct(): LiveData<T> {
  val mediatorLiveData: MediatorLiveData<T> = MediatorLiveData()
  mediatorLiveData.addSource(this) {
    if (it != mediatorLiveData.value) {
      mediatorLiveData.value = it
    }
  }
  return mediatorLiveData
}

class NonNullMediatorLiveData<T> : MediatorLiveData<T>()

fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
  val mediator: NonNullMediatorLiveData<T> = NonNullMediatorLiveData()
  mediator.addSource(this, Observer { it?.let { mediator.value = it } })
  return mediator
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (t: T?) -> Unit) {
  observe(owner, Observer { observer(it) })
}

fun <T> LiveData<out T>.changed(
    owner: LifecycleOwner,
    onChanged: (T) -> Unit
) {
  return nonNull().distinct().observe(owner, onChanged)
}