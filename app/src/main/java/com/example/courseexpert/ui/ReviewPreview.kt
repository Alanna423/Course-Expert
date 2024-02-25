package com.example.courseexpert.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Visibility
import com.example.courseexpert.R
import com.example.courseexpert.data.Review
import com.example.courseexpert.ui.theme.CourseExpertTheme
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ReviewPreview(review: Review, onExpand: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("${review.courseDepartment} ${review.courseNumber}")
            Text("Professor: ${review.professor}")
            Text("Course Difficulty: ${review.courseDifficulty}")
            Text("Professor Difficulty: ${review.professorDifficulty}")
            Text("Recommend to Others: ${if (review.wouldRecommend) "Yes" else "No"}")

            // Add an "Expand" button
            Button(
                onClick = { onExpand() },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
            ) {
                Text("Expand")
            }
        }
    }
}

@Composable
fun ReviewExpanded(review: Review, onBack: () -> Unit) {
    var showTextbookDetails by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Display detailed information
            Text("Course Department: ${review.courseDepartment}")
            Text("Course Number: ${review.courseNumber}")
            Text("Professor: ${review.professor}")
            Text("Course Review: ${review.textBody}")
            Text("Course Difficulty: ${review.courseDifficulty}")
            Text("Professor Difficulty: ${review.professorDifficulty}")
            Text("Recommend to Others: ${if (review.wouldRecommend) "Yes" else "No"}")
            Text("Time Spent per Week: ${review.timePerWeek} hours")

            // Display Use Textbook details if it's set to true
            if (review.requiredTextbook) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Used Textbook: Yes")

                    IconButton(
                        onClick = { showTextbookDetails = !showTextbookDetails },
                        modifier = Modifier
                            .padding(start = 8.dp)
                    ) {
                        Icon(
                            imageVector = if (showTextbookDetails) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (showTextbookDetails) "Hide Details" else "Show Details"
                        )
                    }
                }

                if (showTextbookDetails) {
                    Text("Textbook Details: ${review.requiredTextbook}")
                }
            } else {
                Text("Used Textbook: No")
            }

            // Add a back button
            IconButton(
                onClick = { onBack() },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReviewScreen() {
    val reviewList = remember { mutableStateListOf<Review>() }

    // Mock data for testing
    val mockReview = Review(
        courseDepartment = "CS",
        courseNumber = "101",
        professor = "Dr. Smith",
        textBody = "Great course!",
        courseDifficulty = 3,
        professorDifficulty = 4,
        wouldRecommend = true,
        timePerWeek = 10,
        requiredTextbook = true,
    )

    var selectedReview by remember { mutableStateOf<Review?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ReviewPreview(review = mockReview, onExpand = {
            selectedReview = mockReview
        })

        selectedReview?.let { review ->
            ReviewExpanded(review = review, onBack = {
                selectedReview = null
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    CourseExpertTheme {
        ReviewScreen()
    }
}