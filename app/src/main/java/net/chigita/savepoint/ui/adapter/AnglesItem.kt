package net.chigita.savepoint.ui.adapter

import com.xwray.groupie.databinding.BindableItem
import net.chigita.savepoint.R
import net.chigita.savepoint.databinding.AnglesItemBinding
import net.chigita.savepoint.util.EqualableContentsProvider
import net.chigita.savepoint.vo.Angle

/**
 * Created by chigichan24 on 2019-05-27.
 */
class AnglesItem (
    private val angle: Angle
) : BindableItem<AnglesItemBinding>(angle.uuid.hashCode().toLong()), EqualableContentsProvider {
  override fun getLayout(): Int = R.layout.angles_item

  override fun bind(viewBinding: AnglesItemBinding, position: Int) {
    viewBinding.angleText.text = angle.name
    viewBinding.angleDescription.text = angle.description
  }

  override fun providerEqualableContents(): Array<*> = arrayOf(angle)

  override fun equals(other: Any?): Boolean = isSameContents(other)

  override fun hashCode(): Int = contentsHash()
}