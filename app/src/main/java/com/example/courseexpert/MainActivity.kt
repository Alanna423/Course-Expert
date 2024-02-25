package com.example.courseexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.courseexpert.ui.AddReviewScreen
import com.example.courseexpert.ui.FAQScreen
import com.example.courseexpert.ui.OnboardingScreen
import com.example.courseexpert.ui.ProfileScreen
import com.example.courseexpert.ui.SearchScreen
import com.example.courseexpert.ui.theme.CourseExpertTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

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
fun CourseExpertApp(appNavController: NavHostController = rememberNavController()) {
    val reviewDb = Firebase.firestore

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var showOnboarding by remember { mutableStateOf(true) }

        val backStackEntry by appNavController.currentBackStackEntryAsState()
        var currentScreen = AppScreens.valueOf(backStackEntry?.destination?.route ?: AppScreens.Search.name)

        if (showOnboarding) {
            OnboardingScreen(onLogin = {showOnboarding = false}, reviewDb)
        } else {
            Scaffold(
                bottomBar = {
                    Navbar(appNavController, modifier = Modifier)
                }
            ) { innerPadding ->
                NavHost(
                    navController = appNavController,
                    startDestination = currentScreen.name,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(route = AppScreens.AddReview.name) {
                        AddReviewScreen(reviewDb, onAdd = {appNavController.navigate(AppScreens.Search.name)})
                    }
                    composable(route = AppScreens.FAQ.name) {
                        FAQScreen()
                    }
                    composable(route = AppScreens.Profile.name) {
                        ProfileScreen()
                    }
                    composable(route = AppScreens.Search.name) {
                        SearchScreen(appNavController,reviewDb)
                    }
                }
            }
        }
    }
}

enum class AppScreens() {
    AddReview,
    FAQ,
    Profile,
    Search
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CourseExpertTheme {
        CourseExpertApp()
    }
}