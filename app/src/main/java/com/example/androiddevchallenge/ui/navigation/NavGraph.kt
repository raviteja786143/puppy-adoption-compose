package com.example.androiddevchallenge.ui.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.navigation.Destinations.PET_ID_KEY
import com.example.androiddevchallenge.ui.screen.PetDetailScreen
import com.example.androiddevchallenge.ui.screen.PetListScreen

@Composable
fun NavGraph(startDestination: String = Destinations.PET_LIST) {
	val navController = rememberNavController()
	val actions = remember(navController) { Actions(navController) }
	NavHost(
		navController = navController,
		startDestination = startDestination
	) {
		composable(Destinations.PET_LIST) {
			PetListScreen(selectPet = actions.selectPet)
		}
		composable(
			"${Destinations.PET_DETAIL}/{$PET_ID_KEY}",
			arguments = listOf(
				navArgument(PET_ID_KEY) {
					type = NavType.IntType
				}
			)
		) { backStackEntry ->
			val arguments = requireNotNull(backStackEntry.arguments)
			PetDetailScreen(
				petID = arguments.getInt(PET_ID_KEY, 1),
				upPress = actions.upPress
			)
		}
	}
}

class Actions(navController: NavHostController) {

	val selectPet: (Int) -> Unit = { petID: Int ->
		navController.navigate("${Destinations.PET_DETAIL}/$petID")
	}
	val upPress: () -> Unit = {
		navController.navigateUp()
	}
}

internal val LocalBackDispatcher = staticCompositionLocalOf<OnBackPressedDispatcher> {
	error("No Back Dispatcher provided")
}

object Destinations {
	const val PET_LIST = "petList"
	const val PET_DETAIL = "petDetail"
	const val PET_ID_KEY = "petKey"
}
