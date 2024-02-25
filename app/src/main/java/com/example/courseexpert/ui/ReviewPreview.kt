package com.example.courseexpert.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.courseexpert.data.Review

// a shortened version of a single Review as a search result
@Composable
fun ReviewPreview(review: Review) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.padding(20.dp)
    ) {

        Text("${review.courseDepartment} ${review.courseNumber}")
    }
}