package net.chigita.savepoint.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-15.
 */
class HomeViewModel @Inject constructor(
    private val app: Application
) : AndroidViewModel(app) {
  fun clickFab() {
    Toast.makeText(
        app.applicationContext,
        "clicked",
        Toast.LENGTH_SHORT
    ).show()
  }
}