package com.example.courseexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.courseexpert.ui.OnboardingScreen
import com.example.courseexpert.ui.theme.CourseExpertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseExpertTheme {
                // A surface container using the 'background' color from the theme
                CourseExpertApp()
            }
        }
    }
}

@Composable
fun CourseExpertApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var showOnboarding by remember { mutableStateOf(true) }

        if (showOnboarding) {
            OnboardingScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CourseExpertTheme {
        CourseExpertApp()
    }
}