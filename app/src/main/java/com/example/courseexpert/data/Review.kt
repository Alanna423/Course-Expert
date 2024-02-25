package com.example.courseexpert.data

data class Review(
    val courseDepartment: String = "",
    val courseNumber: String = "",
    val professor: String = "",
    val textBody: String = "",
    val courseDifficulty: Int = 0,
    val professorDifficulty: Int = 0,
    val timePerWeek: Int = 0,
    val wouldRecommend: Boolean = false,
    val requiredTextbook: Boolean = false
)


