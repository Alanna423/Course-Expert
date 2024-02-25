package com.example.courseexpert.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
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
import com.example.courseexpert.ui.submitSearchQuery as submitSearchQuery1

// this component is created from MainActivity.kt's CourseExpertApp Composable

data class Course(
    val subject: String,
    val classNumber: Int,
    val courseTitle: String,
    val description: String
)
@Composable
fun SearchScreen(reviewDb: FirebaseFirestore) {
    var courseNumber by remember { mutableStateOf("") }
    var courseDepartment by remember { mutableStateOf("") }
    var nonNullQuery by remember { mutableStateOf(false) }

    Column() {
        var isExpanded by remember { mutableStateOf(false) }
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded != isExpanded }) {
            for (department in courseDepartments) {
                DropdownMenuItem(text = {Text(department)}, onClick = {
                    courseDepartment = department
                    isExpanded = false
                })
            }
        }

        OutlinedTextField(
            value = courseNumber,
            onValueChange = {courseNumber = it},
            label = {Text("Course Number")},
            modifier = Modifier.padding(8.dp)
        )

        Button(onClick = {
            nonNullQuery = true
            submitSearchQuery1(
                courseDepartment = courseDepartment,
                courseNumber = courseNumber,
                reviewDb = reviewDb
            )
        }) {
            Text("Search")
        }

        if (nonNullQuery) {
            SearchQueryList(reviewDb)
        }
    }
}

fun submitSearchQuery(courseDepartment: String, courseNumber: String, reviewDb: FirebaseFirestore) {
    if (courseDepartment != "" || courseNumber != "") {
        val courseNumberInt = courseNumber.toIntOrNull()

        if (courseNumberInt != null) {
            val course = Course(courseDepartment, courseNumberInt, "Course Title", "Course Description")
            addCoursesToFirestore(course, reviewDb)
        } else {
            Log.e(TAG, "Invalid course number format: $courseNumber")
        }
    } else {
        Log.e(TAG, "Both department and course number must be provided")
    }
}

fun addCoursesToFirestore(course: Course, reviewDb: FirebaseFirestore) {
    val collectionReference = reviewDb.collection("courses")

    collectionReference.add(course)
        .addOnSuccessListener { documentReference ->
            Log.d(TAG, "Document added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document: ", e)
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

fun createCourseList(): List<Course> {
    return listOf(
        Course("ENG", 101, "Introduction to English Composition", "Basic writing skills."),
        Course("MATH", 201, "Calculus I", "Fundamental principles of calculus."),
        Course("BIO", 301, "Biology of Cells", "Cellular structure, function, and processes."),
        Course("PSYCH", 150, "Introduction to Psychology", "Overview of basic concepts in psychology."),
        Course("ART", 110, "Introduction to Art History", "Survey of major art movements and styles."),
        Course("CHEM", 210, "Organic Chemistry I", "Introduction to organic chemistry principles."),
        Course("HIST", 250, "World History: 20th Century", "Examining global events and their impact in the 1900s."),
        Course("CS", 110, "Introduction to Computer Science", "Basic concepts of programming and algorithm design."),
        Course("ECON", 301, "Microeconomics", "Principles of microeconomics and market behavior."),
        Course("PHYS", 101, "General Physics I", "Mechanics, motion, and forces in introductory physics."),
        Course("POLSCI", 202, "Comparative Politics", "Comparative analysis of political systems worldwide."),
        Course("SOC", 220, "Sociology of Culture", "Exploration of cultural phenomena from a sociological perspective."),
        Course("LANG", 150, "Spanish I", "Introductory Spanish language course."),
        Course("MKTG", 310, "Marketing Principles", "Fundamental principles and strategies in marketing."),
        Course("ANTHRO", 110, "Introduction to Anthropology", "Basic concepts in anthropology and cultural diversity."),
        Course("MUSIC", 120, "Music Appreciation", "Exploration of different musical styles and genres."),
        Course("PHIL", 230, "Ethics", "Examination of ethical theories and moral reasoning."),
        Course("GEOG", 301, "Human Geography", "Study of human societies and their spatial organization."),
        Course("LIT", 201, "World Literature", "Survey of literature from various cultures and regions."),
        Course("COMM", 250, "Introduction to Communication Studies", "Basic principles of communication and media studies.")
    )
}

val courseDepartments = listOf("COMP","PSYC","STOR","GEOG","ANTH","CHIN","MUSC")