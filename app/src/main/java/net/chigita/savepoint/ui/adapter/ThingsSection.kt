package net.chigita.savepoint.ui.adapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.xwray.groupie.Group
import com.xwray.groupie.Section
import net.chigita.savepoint.util.changed
import net.chigita.savepoint.vo.Thing

/**
 * Created by chigichan24 on 2019-05-25.
 */

class ThingsSection(
    private val thingLiveData: LiveData<List<Thing>>,
    private val lifecycleOwner: LifecycleOwner
) : Section() {
  init {
    thingLiveData.changed(lifecycleOwner){
      reload(it)
    }
  }

  fun reload(things: List<Thing>) {
    val items = mutableListOf<Group>()
    things.forEach {
      items.add(ThingsItem(it))
    }
    update(items)
  }
}
