package com.example.courseexpert.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseexpert.data.Review
import com.example.courseexpert.ui.theme.CourseExpertTheme

@Composable
fun ReviewPreview(review: Review, onExpand: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .padding(20.dp)
            .clickable { onExpand() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Professor: ${review.professor}")
            Text("Course Difficulty: ${review.courseDifficulty}")
            Text("Professor Difficulty: ${review.professorDifficulty}")
            Text("Recommend to Others: ${if (review.wouldRecommend) "Yes" else "No"}")
        }
    }
}

@Composable
fun ReviewExpanded(review: Review, onBack: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Back", modifier = Modifier.clickable { onBack() })
        }

        Text("Professor: ${review.professor}")
        Text("Course Difficulty: ${review.courseDifficulty}")
        Text("Professor Difficulty: ${review.professorDifficulty}")
        Text("Recommend to Others: ${if (review.wouldRecommend) "Yes" else "No"}")
        Text("Course Department: ${review.courseDepartment}")
        Text("Course Number: ${review.courseNumber}")
        Text("Course Review: ${review.textBody}")
        Text("Time Spent per Week: ${review.timePerWeek} hours")
        Text("Used Textbook: ${if (review.requiredTextbook) "Yes" else "No"}")
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewPreviewPreview() {
    CourseExpertTheme {
        ReviewPreview(
            Review(
                "CS",
                "101",
                "Dr. Smith",
                "Great course!",
                4,
                3,
                7,
                false,
                false
            )
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewExpandedPreview() {
    CourseExpertTheme {
        ReviewExpanded(
            Review(
                "CS",
                "101",
                "Dr. Smith",
                "Great course!",
                4,
                3,
                7,
                false,
                false
            )
        ) {}
    }
}