package net.chigita.savepoint.ui.adapter

import androidx.lifecycle.LifecycleOwner
import com.xwray.groupie.Group
import com.xwray.groupie.Section
import net.chigita.savepoint.vo.Angle

/**
 * Created by chigichan24 on 2019-05-27.
 */
class AnglesSection(
    private val angles: List<Angle>,
    private val lifecycleOwner: LifecycleOwner
) : Section() {
  init {
    reload(angles)
  }

  fun reload(angels: List<Angle>) {
    val items = mutableListOf<Group>()
    angels.forEach {
      items.add(AnglesItem(it))
    }
    update(items)
  }
}