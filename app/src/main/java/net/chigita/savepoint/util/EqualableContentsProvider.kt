package net.chigita.savepoint.util

import java.util.Arrays

/**
 * Created by chigichan24 on 2019-05-25.
 */

interface EqualableContentsProvider {
  fun providerEqualableContents(): Array<*>

  override fun equals(other: Any?): Boolean

  override fun hashCode(): Int

  fun isSameContents(other: Any?): Boolean {
    other ?: return false
    if (other !is EqualableContentsProvider) return false
    if (other::class != this::class) return false
    return other.providerEqualableContents().contentDeepEquals(this.providerEqualableContents())
  }

  fun contentsHash(): Int {
    return Arrays.deepHashCode(arrayOf(this::class, this.providerEqualableContents()))
  }
}