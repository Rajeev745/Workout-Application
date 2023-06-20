package com.example.workoutapplication.fragments

//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.workoutapplication.adapter.ExerciseAdapter
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapplication.R
import com.example.workoutapplication.activities.FinishActivity
import com.example.workoutapplication.adapter.ExerciseAdapter
import com.example.workoutapplication.constants.Constants
import com.example.workoutapplication.databinding.FragmentExerciseBinding
import com.example.workoutapplication.models.Exercise
import java.util.Locale

class ExerciseFragment : Fragment(), TextToSpeech.OnInitListener {

    private var binding: FragmentExerciseBinding? = null
    private var relaxTimer: CountDownTimer? = null
    private var remainingProgress = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseList: ArrayList<Exercise>? = null
    private var exerciseCurrentPosition: Int = -1
    private var tts: TextToSpeech? = null
    private var exerciseAdapter: ExerciseAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tts = TextToSpeech(context, this)
        exerciseList = Constants.getExerciseList()
        setUpRestView()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding?.exerciseRecyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseAdapter(exerciseList!!)
        binding?.exerciseRecyclerView?.adapter = exerciseAdapter
    }

    override fun onDestroyView() {
        binding = null
        if (relaxTimer != null) {
            relaxTimer?.cancel()
            remainingProgress = 0
        }

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroyView()
    }

    private fun setUpRestView() {

        binding?.restProgressBarLayout?.visibility = View.VISIBLE
        binding?.exerciseFragmentText?.text = resources.getString(R.string.resting_time)
        binding?.exerciseProgressBarLayout?.visibility = View.INVISIBLE
        binding?.exerciseImageView?.visibility = View.INVISIBLE
        binding?.upcomingExerciseName?.visibility = View.VISIBLE
        binding?.upcomingExerciseName?.text =
            exerciseList?.get(exerciseCurrentPosition + 1)?.exerciseName
        binding?.upcomingExerciseText?.visibility = View.VISIBLE

        if (relaxTimer != null) {
            relaxTimer?.cancel()
            remainingProgress = 0
        }
        setUpProgressBarView()
    }

    private fun setUpProgressBarView() {
        binding?.restProgressBar?.progress = remainingProgress

        relaxTimer = object : CountDownTimer(2000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                remainingProgress++
                binding?.restProgressBar?.progress = 20 - remainingProgress
                binding?.restProgressBarTimer?.text = (20 - remainingProgress).toString()
            }

            override fun onFinish() {
                exerciseCurrentPosition++
                exerciseList!![exerciseCurrentPosition].isSelected = true
                exerciseAdapter?.notifyDataSetChanged()
                setUpExerciseView()
            }
        }.start()
    }

    private fun setUpExerciseView() {
        binding?.restProgressBarLayout?.visibility = View.INVISIBLE
        binding?.exerciseFragmentText?.text =
            exerciseList?.get(exerciseCurrentPosition)?.exerciseName
        binding?.exerciseProgressBarLayout?.visibility = View.VISIBLE
        binding?.exerciseImageView?.visibility = View.VISIBLE
        binding?.exerciseImageView?.setImageResource(exerciseList?.get(exerciseCurrentPosition)?.exersiceImage!!)
        binding?.upcomingExerciseName?.visibility = View.INVISIBLE
        binding?.upcomingExerciseText?.visibility = View.INVISIBLE

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![exerciseCurrentPosition].exerciseName)
        setUpExerciseProgressBarView()
    }

    private fun setUpExerciseProgressBarView() {
        binding?.exerciseProgressBar?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.exerciseProgressBar?.progress = 30 - exerciseProgress
                binding?.exerciseProgressBarTimer?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                exerciseList!![exerciseCurrentPosition].isSelected = false
                exerciseList!![exerciseCurrentPosition].isCompleted = true
                exerciseAdapter?.notifyDataSetChanged()
                if (exerciseCurrentPosition < exerciseList?.size!! - 1) {
                    setUpRestView()
                } else {
                    val intent = Intent(context, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.UK)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }
        } else {
            Log.d("tts", "Initialization error")
        }
    }

    private fun speakOut(word: String) {
        tts?.speak(word, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}