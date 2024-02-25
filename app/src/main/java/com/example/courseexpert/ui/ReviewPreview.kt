package com.example.courseexpert.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.courseexpert.data.Review

// a shortened version of a single Review as a search result
@Composable
fun ReviewPreview(review: Review) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text("${review.courseDepartment}")
    }
}