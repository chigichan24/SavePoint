package net.chigita.savepoint.ui.adapter

import androidx.lifecycle.LifecycleOwner
import com.xwray.groupie.Group
import com.xwray.groupie.Section
import net.chigita.savepoint.vo.Thing

/**
 * Created by chigichan24 on 2019-05-25.
 */

class ThingsSection(
    private val things: List<Thing>,
    private val lifecycleOwner: LifecycleOwner
) : Section() {
  init {
    reload(things)
  }

  fun reload(things: List<Thing>) {
    val items = mutableListOf<Group>()
    things.forEach {
      items.add(ThingsItem(it))
    }
    update(items)
  }
}
