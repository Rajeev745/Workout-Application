package com.example.workoutapplication.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapplication.R
import com.example.workoutapplication.databinding.ActivityExerciseBinding
import com.example.workoutapplication.databinding.CustomDialogForBackButtonBinding
import com.example.workoutapplication.fragments.ExerciseFragment

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.exercise_fragment_container, ExerciseFragment()).commit()
        setUpCustomToolbar()
    }

    override fun onBackPressed() {
        setUpCustomDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setUpCustomDialog() {
        val customDialog = Dialog(this@ExerciseActivity)
        val dialogBinding: CustomDialogForBackButtonBinding = CustomDialogForBackButtonBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)

        dialogBinding.cancelDialogBtn.setOnClickListener {
            customDialog.dismiss()
        }

        dialogBinding.proceedDialogBtn.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }

        customDialog.show()
    }

    private fun setUpCustomToolbar() {
        setSupportActionBar(binding?.exerciseCustomToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.exerciseCustomToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}