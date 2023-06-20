package com.example.workoutapplication.constants

import com.example.workoutapplication.R
import com.example.workoutapplication.models.Exercise

class Constants {

    companion object {
        fun getExerciseList(): ArrayList<Exercise> {
            val exerciseList = ArrayList<Exercise>()

            exerciseList.add(Exercise(1, "Burpees", R.drawable.burpee_image, false, false))
            exerciseList.add(Exercise(2, "Chin Ups", R.drawable.chin_up_image, false, false))
            exerciseList.add(Exercise(3, "Down Ups", R.drawable.down_ups_image, false, false))
            exerciseList.add(
                Exercise(
                    4, "Jumping Squats", R.drawable.jumping_squats_image, false, false
                )
            )
            exerciseList.add(Exercise(5, "Lunges", R.drawable.lunges_image, false, false))
            exerciseList.add(Exercise(6, "Plank", R.drawable.plank_image, false, false))
            exerciseList.add(Exercise(7, "Press Up", R.drawable.press_up_image, false, false))
            exerciseList.add(Exercise(8, "Running", R.drawable.running_image, false, false))
            exerciseList.add(Exercise(9, "Sit Ups", R.drawable.sit_up_image, false, false))
            exerciseList.add(Exercise(10, "Toe Touch", R.drawable.toe_touch_image, false, false))

            return exerciseList
        }
    }
}