package the.store.presentation.basket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.core.ui.widget.TheStoreOnBackToolbar
import com.example.theme.R

@Composable
fun BasketScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        TheStoreOnBackToolbar(
            R.string.basket,
            pressOnBack = { navController.popBackStack() }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .weight(1f)
        )
    }
}