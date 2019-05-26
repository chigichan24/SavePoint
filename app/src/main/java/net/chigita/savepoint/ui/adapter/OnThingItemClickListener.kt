package net.chigita.savepoint.ui.adapter

import net.chigita.savepoint.vo.Thing


/**
 * Created by chigichan24 on 2019-05-27.
 */
interface OnThingItemClickListener {
    fun onClick(thing: Thing)
    fun onClickEdit(thing: Thing)
}