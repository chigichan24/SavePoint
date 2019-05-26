package net.chigita.savepoint.ui.adapter

import com.xwray.groupie.databinding.BindableItem
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.ThingsItemBinding
import net.chigita.savepoint.util.EqualableContentsProvider
import net.chigita.savepoint.vo.Thing

/**
 * Created by chigichan24 on 2019-05-25.
 */
class ThingsItem(
    private val thing: Thing,
    private val onThingItemClickListener: OnThingItemClickListener
) : BindableItem<ThingsItemBinding>(thing.uuid.hashCode().toLong()), EqualableContentsProvider {

  override fun getLayout(): Int = R.layout.things_item

  override fun bind(viewBinding: ThingsItemBinding, position: Int) {
    viewBinding.thingText.text = thing.name
    viewBinding.setting.setOnClickListener {
      onThingItemClickListener.onClickEdit(thing)
    }
  }

  override fun providerEqualableContents(): Array<*> = arrayOf(thing)

  override fun equals(other: Any?): Boolean = isSameContents(other)

  override fun hashCode(): Int = contentsHash()
}