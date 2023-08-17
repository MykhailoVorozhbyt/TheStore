package com.example.core.utils.extensions.modifiers.fab_border_modifier

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.core.navigation.Graph

@Composable
fun BottomBarFloatingActionButton(navController: NavHostController) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = {
            Graph.Basket.route.let {
                navController.navigate(it) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        contentColor = Color.Black,
        backgroundColor = Color.White,
        modifier = Modifier.fabBorderModifier()
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
    }
}