package com.example.courseexpert.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.courseexpert.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

// this component is created from MainActivity.kt's CourseExpertApp Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen() {
    var courseDepartment by remember {mutableStateOf("")}
    var courseNumber by remember {mutableStateOf("")}
    var professor by remember { mutableStateOf("")}
    var courseReview by remember { mutableStateOf("")}
    var courseDifficulty by remember{ mutableStateOf("")}
    var professorDifficulty by remember{ mutableStateOf("")}
    var recommendToOthers by remember { mutableStateOf(false) }
    var timeSpentPerWeek by remember { mutableStateOf(1) }
    var usedTextbook by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Add Review")
                },
                actions = {
                    IconButton(
                        onClick = {
                            // Handle submit button click
                        }
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Submit")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(value = courseNumber,
                onValueChange = {courseNumber = it},
                label = {Text("Course Number")},
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
        }
    }
}