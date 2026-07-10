package jets.iti.yousef



import androidx.compose.runtime.*


import jets.iti.yousef.persentation.navigation.AppNavigation
import jets.iti.yousef.persentation.theme.NewsScopeTheme
import jets.iti.yousef.di.appModule
import org.koin.compose.KoinApplication




import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        NewsScopeTheme {
            AppNavigation()
        }
    }
}