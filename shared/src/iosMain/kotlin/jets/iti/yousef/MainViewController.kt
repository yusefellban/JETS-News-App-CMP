package jets.iti.yousef

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import jets.iti.yousef.di.appModule
import jets.iti.yousef.di.platformModule

fun MainViewController() = ComposeUIViewController { 
    if (org.koin.core.context.GlobalContext.getOrNull() == null) {
        startKoin {
            modules(appModule, platformModule)
        }
    }
    App() 
}