package the.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.theme.TheStoreTheme
import dagger.hilt.android.AndroidEntryPoint
import the.store.navigation.RootNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheStoreTheme {
                val navGraph = rememberNavController()
                RootNavGraph(navGraph)
            }
        }
    }
}