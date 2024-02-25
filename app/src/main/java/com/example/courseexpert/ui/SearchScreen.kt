package com.example.courseexpert.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.courseexpert.R
import com.example.courseexpert.data.Review
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

//import com.example.courseexpert.ui.submitSearchQuery as submitSearchQuery1

// this component is created from MainActivity.kt's CourseExpertApp Composable

data class Course(
    val subject: String,
    val classNumber: Int,
    val courseTitle: String,
    val description: String
)
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController, reviewDb: FirebaseFirestore) {
    var courseNumber by remember { mutableStateOf("") }
    var courseDepartment by remember { mutableStateOf("") }
    var nonNullQuery by remember { mutableStateOf(false) }

    val backgroundImage: Painter = painterResource(id = R.drawable.background)
    val gloriaFontFamily = FontFamily(Font(R.font.gloria))
    val gloriaStyle = androidx.compose.ui.text.TextStyle(fontFamily = gloriaFontFamily, fontWeight = FontWeight.Normal)

    Image(
        painter = backgroundImage,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        var isExpanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }) {
            OutlinedTextField(
                value = courseDepartment,
                onValueChange = { courseDepartment = it },
                readOnly = true,
                label = { Text("Course Department") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier.menuAnchor().padding(8.dp)
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                createCourseList().forEach { course ->
                    DropdownMenuItem(text = { Text("${course.subject}") }, onClick = { courseDepartment = course.subject })
                }
            }
        }

        OutlinedTextField(
            value = courseNumber,
            onValueChange = { courseNumber = it },
            label = { Text("Course Number") },
            modifier = Modifier.padding(8.dp)
        )

        Button(onClick = {
            nonNullQuery = true
        }) {
            Text("Search")
        }

        if (nonNullQuery) {
            SearchQueryList(navController, courseDepartment, courseNumber, reviewDb)
        }
    }
}

@Composable
fun SearchQueryList(navController: NavHostController, courseDepartment: String, courseNumber: String, reviewDb: FirebaseFirestore) {
    val reviewList = remember { mutableStateListOf<Review>() }

    if (courseDepartment != "") {
        LaunchedEffect(key1 = 6) {
            reviewDb.collection("reviews").whereEqualTo("courseDepartment", courseDepartment).get()
                .addOnSuccessListener { list ->
                    for (item in list) {
                        val review = item.toObject(Review::class.java)
                        reviewList.add(review)
                    }
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ReviewPreview composable for each review
        LazyColumn {
            items(reviewList) { review ->
                var isExpanded by remember { mutableStateOf(false) }

                if (isExpanded) {
                    ReviewExpanded(review, onBack = {
                        isExpanded = false
                    })
                } else {
                    ReviewPreview(review, onExpand = {
                        isExpanded = true
                    })
                }
            }
        }
    }
}

@Composable
fun ReviewExpanded(review: Review, onBack: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.padding(8.dp)
    ) {
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
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick =
                onBack) {Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")}
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