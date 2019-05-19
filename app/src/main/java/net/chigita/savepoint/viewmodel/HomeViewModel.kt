package net.chigita.savepoint.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import net.chigita.savepoint.R
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-05-15.
 */
internal class HomeViewModel : ViewModel() {
  @Inject lateinit var navController: NavController
  fun clickFab() {
    navController.navigate(R.id.action_main_to_register)
  }
}