package net.chigita.savepoint.viewmodel

import android.content.Context
import android.widget.Toast


/**
 * Created by chigichan24 on 2019-05-24.
 */

fun onError(context: Context, e: Exception) {
    Toast.makeText(
        context,
        e.message,
        Toast.LENGTH_LONG
    ).show()
}