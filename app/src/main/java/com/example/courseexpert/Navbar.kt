package com.example.courseexpert

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Navbar(navController: NavController, modifier: Modifier) {
    Surface() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            AppScreens.values().forEach { screen ->
                Button(
                    onClick = {navController.navigate(screen.name)}
                ) {
                    Text(screen.name)
                }
            }
        }
    }
}