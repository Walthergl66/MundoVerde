package com.mundoverde.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mundoverde.ui.home.Home
import com.mundoverde.ui.cultivos.AddCrop
import com.mundoverde.ui.cultivos.CropDetail
import com.mundoverde.ui.cultivos.CropList
import com.mundoverde.ui.settings.Settings
import com.mundoverde.ui.user.Profile
import com.mundoverde.ui.user.TasksCalendar

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { Home(onNavigate = { route -> navController.navigate(route) }) }
        composable(Routes.ADD_CROP) { AddCrop(onNavigateBack = { navController.popBackStack() }) }
        composable("${Routes.CROP_DETAIL}/{cropId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("cropId") ?: ""
            CropDetail(cropId = id, onNavigateBack = { navController.popBackStack() })
        }
        composable(Routes.CROP_LIST) { CropList(onNavigate = { route -> navController.navigate(route) }) }
        composable(Routes.TASKS_CALENDAR) { TasksCalendar() }
        composable(Routes.PROFILE) { Profile(onNavigateBack = { navController.popBackStack() }) }
        composable(Routes.SETTINGS) { Settings() }
    }
}

object Routes {
    const val HOME = "home"
    const val ADD_CROP = "add_crop"
    const val CROP_DETAIL = "crop_detail"
    const val CROP_LIST = "crop_list"
    const val TASKS_CALENDAR = "tasks_calendar"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"
}
