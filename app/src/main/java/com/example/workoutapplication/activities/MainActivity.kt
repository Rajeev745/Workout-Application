package com.example.workoutapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapplication.R
import com.example.workoutapplication.fragments.StartFragment
import com.example.workoutapplication.fragments.WelcomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.welcome_fragment_layout, WelcomeFragment()).commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.start_fragment_layout, StartFragment()).commit()
    }
}