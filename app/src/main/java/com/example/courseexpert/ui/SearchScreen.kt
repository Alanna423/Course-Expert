package com.example.courseexpert.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.courseexpert.data.Review
import com.google.firebase.firestore.FirebaseFirestore

// this component is created from MainActivity.kt's CourseExpertApp Composable
@Composable
fun SearchScreen(reviewDb: FirebaseFirestore) {
    val reviewList = remember { mutableStateListOf<Review>() }

    reviewDb.collection("reviews").get()
        .addOnSuccessListener{ list ->
        for (item in list) {
            val review = item.toObject(Review::class.java)
            reviewList.add(review)
        }
    }

    for (review in reviewList) {
        ReviewPreview(review)
    }
}