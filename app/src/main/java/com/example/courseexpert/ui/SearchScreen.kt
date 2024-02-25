package com.example.courseexpert.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.courseexpert.data.Review
import com.google.firebase.firestore.FirebaseFirestore

// this component is created from MainActivity.kt's CourseExpertApp Composable
@Composable
fun SearchScreen(reviewDb: FirebaseFirestore) {
    var courseNumber by remember { mutableStateOf("") }
    var courseDepartment by remember { mutableStateOf("") }
    var nonNullQuery by remember { mutableStateOf(false) }

    Column() {
        var isExpanded by remember { mutableStateOf(false) }
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded != isExpanded }) {
            for (department in courseDepartments) {
                DropdownMenuItem(text = {Text(department)}, onClick = {})
            }
        }

        OutlinedTextField(
            value = courseNumber,
            onValueChange = {courseNumber = it},
            label = {Text("Course Number")},
            modifier = Modifier.padding(8.dp)
        )

        Button(onClick = {nonNullQuery = true}
        ) {
            Text("Search")
        }

        if (nonNullQuery) {
            SearchQueryList(reviewDb)
        }
    }
}

@Composable
fun submitSearchQuery(courseDepartment: String, courseNumber: String) {
    if (courseDepartment!="" || courseNumber!="") {

    }
}

@Composable
fun SearchQueryList(reviewDb: FirebaseFirestore) {
    val reviewList = remember { mutableStateListOf<Review>() }

    LaunchedEffect(key1 = 1) {
        reviewDb.collection("reviews").get()
            .addOnSuccessListener{ list ->
                for (item in list) {
                    val review = item.toObject(Review::class.java)
                    reviewList.add(review)
                }
            }
    }

    LazyColumn() {
        items(reviewList) { review ->
            ReviewPreview(review)
        }
    }
}

val courseDepartments = listOf("COMP","PSYC","STOR","GEOG","ANTH","CHIN","MUSC")