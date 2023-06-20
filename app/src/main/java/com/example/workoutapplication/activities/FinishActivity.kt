package com.example.workoutapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapplication.R
import com.example.workoutapplication.databinding.ActivityFinishBinding
import com.example.workoutapplication.fragments.ExerciseFragment
import com.example.workoutapplication.fragments.FinishFragment

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.exerciseCustomToolbar)
        supportFragmentManager.beginTransaction()
            .add(R.id.finish_exercise_fragment_layout, FinishFragment()).commit()
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.exerciseCustomToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}