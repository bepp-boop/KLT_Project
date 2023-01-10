package com.example.klt_project.ui.home.ui.scheduleFragment


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.klt_project.DataList
import com.example.klt_project.R
import kotlinx.android.synthetic.main.fragment_about_us.view.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import kotlinx.android.synthetic.main.list_interaction.view.*
import java.util.*

class ScheduleItemAdapter(
    private val context: ScheduleFragment,
    private val dataset: List<Schedule>
) : RecyclerView.Adapter<ScheduleItemAdapter.ScheduleItemViewHolder>(){
    private val arrayHour:Array<Int> = arrayOf(0,0,0,0)
    private val arrayMinutes:Array<Int> = arrayOf(0,0,0,0)
    private val arraySeconds:Array<Int> = arrayOf(0,0,0,0)

    class ScheduleItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val scheduleLayout: LinearLayout = view.findViewById(R.id.schedule_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_interaction, parent, false)
        return ScheduleItemViewHolder(adapterLayout)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {
        lateinit var sentences:Array<String>

        var counter = 0
        val item = dataset[position]

        sentences = context.resources.getStringArray(item.stringScheduleResourceId)

        var startHour = 0
        var stopHour = 0

        var startMinute = 0
        var stopMinute = 0

        var startSecond = 0
        var stopSecond = 0

        holder.scheduleLayout.interaction.text = sentences[0]
        holder.scheduleLayout.setOnClickListener{

            val calendar: Calendar = Calendar.getInstance()

            if(counter == 0){
                holder.scheduleLayout.setBackgroundColor(Color.RED)
                holder.scheduleLayout.interaction.text = sentences[1]

                startHour = calendar.get(Calendar.HOUR_OF_DAY)
                startMinute = calendar.get(Calendar.MINUTE)
                startSecond = calendar.get(Calendar.SECOND)

                Toast.makeText(context.requireContext(), "Starting timer", Toast.LENGTH_SHORT).show()
                counter = 1
            }else if(counter == 1){
                holder.scheduleLayout.setBackgroundResource(R.color.mission_card)
                holder.scheduleLayout.interaction.text = sentences[0]

                stopHour = calendar.get(Calendar.HOUR_OF_DAY)
                stopMinute = calendar.get(Calendar.MINUTE)
                stopSecond = calendar.get(Calendar.SECOND)

                Toast.makeText(context.requireContext(), "Stopping timer", Toast.LENGTH_SHORT).show()
                counter = 0
            }

            val totalHours:Int = if(stopHour > startHour) {
                stopHour - startHour
            }else{
                startHour - stopHour
            }
            val totalMinutes:Int = if(stopMinute > startMinute) {
                stopMinute - startMinute
            }else{
                startMinute - stopMinute
            }
            val totalSeconds:Int = if(stopSecond > startSecond) {
                stopSecond - startSecond
            }else{
                startSecond - stopSecond
            }

            arrayHour[position] = totalHours
            arrayMinutes[position] = totalMinutes
            arraySeconds[position] = totalSeconds

            DataList.timeElapsed["hour"] = arrayHour
            DataList.timeElapsed["minutes"] = arrayMinutes
            DataList.timeElapsed["seconds"] = arraySeconds
        }
    }

    override fun getItemCount() = dataset.size
}