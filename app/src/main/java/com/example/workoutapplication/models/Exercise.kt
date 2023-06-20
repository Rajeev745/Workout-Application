package com.example.workoutapplication.models

data class Exercise(
    val id: Int,
    val exerciseName: String,
    val exersiceImage: Int,
    var isCompleted: Boolean,
    var isSelected: Boolean
)
