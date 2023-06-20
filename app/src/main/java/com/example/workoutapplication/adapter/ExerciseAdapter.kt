package com.example.workoutapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapplication.R
import com.example.workoutapplication.databinding.ItemExreciseLayoutBinding
import com.example.workoutapplication.models.Exercise

class ExerciseAdapter(private val exerciseItem: ArrayList<Exercise>): RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExreciseLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Exercise = exerciseItem[position]
        holder.exerciseStatusNumber.text = item.id.toString()

        when {
            item.isSelected -> {
                holder.exerciseStatusNumber.setBackgroundColor(R.color.primary_color)
                holder.exerciseStatusNumber.setTextColor(R.color.white)
            }
            item.isCompleted -> {
                holder.exerciseStatusNumber.setBackgroundColor(R.color.text_color_dark)
                holder.exerciseStatusNumber.setTextColor(R.color.text_color_dark)
            }
            else -> {
                holder.exerciseStatusNumber.setTextColor(R.color.text_color_dark)
                holder.exerciseStatusNumber.setBackgroundColor(R.color.divider_color)
            }
        }
    }

    override fun getItemCount(): Int {
        return exerciseItem.size
    }

    class ViewHolder(binding: ItemExreciseLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val exerciseStatusNumber = binding.exerciseStatusNumber
    }
}