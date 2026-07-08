package jets.iti.yousef.persentation.navigation

sealed class NavScreen(val route: String) {
    data object Splash : NavScreen("splash")
    data object Home : NavScreen("home")

}