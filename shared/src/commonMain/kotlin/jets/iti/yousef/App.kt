package jets.iti.yousef



import androidx.compose.runtime.*


import jets.iti.yousef.persentation.navigation.AppNavigation
import jets.iti.yousef.persentation.theme.NewsScopeTheme
import jets.iti.yousef.di.appModule
import org.koin.compose.KoinApplication




@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        NewsScopeTheme {
            AppNavigation()
        }
    }
}