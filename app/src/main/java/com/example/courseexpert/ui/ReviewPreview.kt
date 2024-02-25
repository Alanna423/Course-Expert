package com.example.courseexpert.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courseexpert.data.Review

// a shortened version of a single Review as a search result
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReviewPreview(review: Review) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = true }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${review.courseDepartment} ${review.courseNumber}", style = LocalTextStyle.current.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Expand")
                }
            }
            if (expanded) {
                ReviewExpanded(review = review)
            }
        }
    }
}

@Composable
fun ReviewExpanded(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Professor: ${review.professor}", style = LocalTextStyle.current.copy(fontSize = 16.sp))
        Text("Course Difficulty: ${review.courseDifficulty}", style = LocalTextStyle.current.copy(fontSize = 16.sp))
        Text("Professor Difficulty: ${review.professorDifficulty}", style = LocalTextStyle.current.copy(fontSize = 16.sp))
        Text("Recommend to Others: ${if (review.wouldRecommend) "Yes" else "No"}", style = LocalTextStyle.current.copy(fontSize = 16.sp))
    }
}