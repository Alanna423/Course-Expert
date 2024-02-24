package com.example.courseexpert.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseexpert.ui.theme.CourseExpertTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen() {
    var courseDepartment by remember { mutableStateOf("") }
    var courseNumber by remember { mutableStateOf("") }
    var professor by remember { mutableStateOf("") }
    var courseReview by remember { mutableStateOf("") }
    var courseDifficulty by remember { mutableStateOf(1) }
    var professorDifficulty by remember { mutableStateOf(1) }
    var recommendToOthers by remember { mutableStateOf(false) }
    var timeSpentPerWeek by remember { mutableStateOf(1) }
    var useTextbook by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = courseDepartment,
            onValueChange = { courseDepartment = it },
            label = { Text("Course Department") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = courseNumber,
            onValueChange = { courseNumber = it },
            label = { Text("Course Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Slider(
            value = courseDifficulty.toFloat(),
            onValueChange = { courseDifficulty = it.toInt() },
            valueRange = 1f..5f,
            steps = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Course Difficulty: $courseDifficulty")
        }

        Slider(
            value = professorDifficulty.toFloat(),
            onValueChange = { professorDifficulty = it.toInt() },
            valueRange = 1f..5f,
            steps = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Professor Difficulty: $professorDifficulty")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Recommend to Others")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = recommendToOthers,
                onCheckedChange = { recommendToOthers = it },
            )
        }

        Slider(
            value = timeSpentPerWeek.toFloat(),
            onValueChange = { timeSpentPerWeek = it.toInt() },
            valueRange = 1f..20f,
            steps = 19,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Time Spent per Week: $timeSpentPerWeek hours")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Did you use Textbook")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = useTextbook,
                onCheckedChange = { useTextbook = it },
            )
        }

        Button(
            onClick = {
                // add submission logic
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "Submit")
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddReviewScreenPreview() {
    CourseExpertTheme {
        AddReviewScreen()
    }
}