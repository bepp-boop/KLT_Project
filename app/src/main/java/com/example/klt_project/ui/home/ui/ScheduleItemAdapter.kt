package com.example.klt_project.ui.home.ui


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.klt_project.R

class ScheduleItemAdapter(
    private val context: ScheduleFragment,
    private val dataset: List<Schedule>
) : RecyclerView.Adapter<ScheduleItemAdapter.ScheduleItemViewHolder>(){

    class ScheduleItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val scheduleLayout: LinearLayout = view.findViewById(R.id.schedule_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemAdapter.ScheduleItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_interaction, parent, false)
        return ScheduleItemAdapter.ScheduleItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ScheduleItemAdapter.ScheduleItemViewHolder, position: Int) {

        val item = dataset[position]
        holder.scheduleLayout.setOnClickListener{
            Toast.makeText(context.requireContext(), "Starting timer", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = dataset.size
}