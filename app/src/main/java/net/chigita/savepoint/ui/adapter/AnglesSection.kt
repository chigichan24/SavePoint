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
    private val lifecycleOwner: LifecycleOwner,
    private val onClickAngle: ((Angle) -> Unit)? = null
) : Section() {

  private val onAngleItemClickListener = object : OnAngleItemClickListener {
    override fun onClick(angle: Angle) {
      onClickAngle?.invoke(angle)
    }
  }

  init {
    reload(angles)
  }

  fun reload(angels: List<Angle>) {
    val items = mutableListOf<Group>()
    angels.forEach {
      items.add(AnglesItem(it, onAngleItemClickListener))
    }
    update(items)
  }
}