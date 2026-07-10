package jets.iti.yousef.persentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jets.iti.yousef.persentation.homescreen.HomeScreen
import jets.iti.yousef.persentation.splashSceen.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.Splash.route
    ) {
        composable(NavScreen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(NavScreen.Home.route) {
                        // remove Splash from back stack so back button doesn't return to it
                        popUpTo(NavScreen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(NavScreen.Home.route) {
            HomeScreen()
        }
    }
}