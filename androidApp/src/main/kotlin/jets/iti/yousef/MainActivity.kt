package jets.iti.yousef

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import io.ktor.client.HttpClient
import jets.iti.yousef.core.network.ArticleService
import jets.iti.yousef.core.network.httpClient
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val articleService = ArticleService()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }


        lifecycleScope.launch {
            try {
                val articles = articleService.fetchArticles()

                articles.forEach {
                    Log.d("NewsAPI", "Title: ${it.title}")
                    Log.d("NewsAPI", "Author: ${it.author}")
                    Log.d("NewsAPI", "Image: ${it.imageUrl}")
                    Log.d("NewsAPI", "-------------------------")
                }

            } catch (e: Exception) {
                Log.e("NewsAPI", "Error: ${e.message}", e)
            }
        }
    }

    }

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}