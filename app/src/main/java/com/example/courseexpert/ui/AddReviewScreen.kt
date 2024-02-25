package com.example.courseexpert.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.courseexpert.data.Review
import com.google.firebase.firestore.FirebaseFirestore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.courseexpert.R


@Composable
fun AddReviewScreen(reviewDb: FirebaseFirestore) {
    var courseDepartment by remember { mutableStateOf("") }
    var courseNumber by remember { mutableStateOf("") }
    var professor by remember { mutableStateOf("") }
    var courseReview by remember { mutableStateOf("") }
    var courseDifficulty by remember { mutableStateOf(1) }
    var professorDifficulty by remember { mutableStateOf(1) }
    var recommendToOthers by remember { mutableStateOf(false) }
    var timeSpentPerWeek by remember { mutableStateOf(1) }
    var useTextbook by remember { mutableStateOf(false) }

    val backgroundImage: Painter = painterResource(id = R.drawable.background)
    val gloriaFontFamily = FontFamily(Font(R.font.gloria))
    val gloriaStyle = androidx.compose.ui.text.TextStyle(fontFamily = gloriaFontFamily, fontWeight = FontWeight.Normal)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Text("Add a Review", fontSize = 40.sp, modifier = Modifier.align(Alignment.TopCenter).padding(top = 20.dp), style = gloriaStyle)
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = courseDepartment,
                onValueChange = { courseDepartment = it },
                label = { Text("Course Department") },
                modifier = Modifier
                    .padding(8.dp, top = 100.dp).width(200.dp)
            )

            OutlinedTextField(
                value = courseNumber,
                onValueChange = { courseNumber = it },
                label = { Text("Course Number") },
                modifier = Modifier
                    .padding(8.dp).padding(top = 100.dp)
            )
        }
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 200.dp, bottom = 25.dp)) {
            Text("Course Difficulty: $courseDifficulty")
            Slider(
                value = courseDifficulty.toFloat(),
                onValueChange = { courseDifficulty = it.toInt() },
                valueRange = 1f..5f,
                steps = 4,
                modifier = Modifier
                    .padding(8.dp)
            )

            Text("Professor Difficulty: $professorDifficulty")
            Slider(
                value = professorDifficulty.toFloat(),
                onValueChange = { professorDifficulty = it.toInt() },
                valueRange = 1f..5f,
                steps = 4,
                modifier = Modifier
                    .padding(8.dp)
            )

            Text(
                "Time Spent per Week: $timeSpentPerWeek hours"
            )
            Slider(
                value = timeSpentPerWeek.toFloat(),
                onValueChange = { timeSpentPerWeek = it.toInt() },
                valueRange = 1f..20f,
                steps = 19,
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        Row(
            modifier = Modifier.padding(8.dp, top = 500.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Recommend to Others")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = recommendToOthers,
                onCheckedChange = { recommendToOthers = it },
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp).padding(top=450.dp, bottom = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Did you use Textbook")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = useTextbook,
                onCheckedChange = { useTextbook = it },
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 570.dp)
        ) {
            Text("Course Review")
            BasicTextField(
                value = courseReview,
                onValueChange = { courseReview = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .border(1.dp, MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
                    .heightIn(min = 100.dp)
            )
        }

        Button(
            onClick = {
                reviewDb.collection("reviews")
                    .add(Review(courseDepartment,courseNumber,professor))
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            },
            modifier = Modifier.padding(8.dp).align(Alignment.BottomCenter)
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "Submit")
            Text("Submit")
        }
    }
}