package the.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import the.store.navigation.RootNavGraph
import the.store.ui.theme.TheStoreTheme

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