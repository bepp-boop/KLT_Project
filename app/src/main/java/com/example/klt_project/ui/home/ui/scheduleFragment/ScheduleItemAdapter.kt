package com.example.klt_project.ui.home.ui.scheduleFragment


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.klt_project.DataList
import com.example.klt_project.DataList.buttonArray
import com.example.klt_project.R
import com.example.klt_project.ui.slideshow.SlideshowViewModel
import kotlinx.android.synthetic.main.fragment_about_us.view.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import kotlinx.android.synthetic.main.list_interaction.view.*
import java.util.*
import kotlin.properties.Delegates


class ScheduleItemAdapter(
    private val context: ScheduleFragment,
    private val dataset: List<Schedule>
) : RecyclerView.Adapter<ScheduleItemAdapter.ScheduleItemViewHolder>() {

    private var savedCounter by Delegates.notNull<Int>()
    var pos by Delegates.notNull<Int>()
    private val slideshowViewModel =
        ViewModelProvider(context.requireActivity())[SlideshowViewModel::class.java]

    private val timer = Timer()
    lateinit var dataHelper: DataHelper

    class ScheduleItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scheduleLayout: LinearLayout = view.findViewById(R.id.schedule_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_interaction, parent, false)
        return ScheduleItemViewHolder(adapterLayout)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {

        dataHelper = DataHelper(context.requireContext())

        lateinit var sentences: Array<String>
        pos = position
        val item = dataset[position]

        sentences = context.resources.getStringArray(item.stringScheduleResourceId)
        holder.scheduleLayout.interaction.text = sentences[0]

        //make an array using liveData inside an array that holds the position and if the position was pressed or not
        slideshowViewModel.pressed.observe(this.context.viewLifecycleOwner) {
            when (holder.layoutPosition) {
                0 -> if (it[0] == 1) {
                    holder.scheduleLayout.setBackgroundColor(Color.RED)
                    holder.scheduleLayout.interaction.text = sentences[1]
                } else if (it[0] == 0) {
                    holder.scheduleLayout.setBackgroundResource(R.color.mission_card)
                    holder.scheduleLayout.interaction.text = sentences[0]
                }

                1 -> if (it[1] == 1) {
                    holder.scheduleLayout.setBackgroundColor(Color.RED)
                    holder.scheduleLayout.interaction.text = sentences[1]

                } else if (it[1] == 0) {
                    holder.scheduleLayout.setBackgroundResource(R.color.mission_card)
                    holder.scheduleLayout.interaction.text = sentences[0]
                }

                2 -> if (it[2] == 1) {
                    holder.scheduleLayout.setBackgroundColor(Color.RED)
                    holder.scheduleLayout.interaction.text = sentences[1]

                } else if (it[2] == 0) {
                    holder.scheduleLayout.setBackgroundResource(R.color.mission_card)
                    holder.scheduleLayout.interaction.text = sentences[0]
                }

                3 -> if (it[3] == 1) {
                    holder.scheduleLayout.setBackgroundColor(Color.RED)
                    holder.scheduleLayout.interaction.text = sentences[1]

                } else if (it[3] == 0) {
                    holder.scheduleLayout.setBackgroundResource(R.color.mission_card)
                    holder.scheduleLayout.interaction.text = sentences[0]
                }
            }
            savedCounter = it[position]
        }


        var localCounter = savedCounter
        Log.d("position", "pos $position")
        Log.d("position", "oldPos ${holder.oldPosition}")
        holder.scheduleLayout.setOnClickListener {

            if (localCounter == 0) {
                holder.scheduleLayout.setBackgroundColor(Color.RED)
                holder.scheduleLayout.interaction.text = sentences[1]

                startStopAction(localCounter)
                Toast.makeText(context.requireContext(), "Starting timer", Toast.LENGTH_SHORT)
                    .show()
                localCounter = 1

            } else if (localCounter == 1) {
                holder.scheduleLayout.setBackgroundResource(R.color.mission_card)
                holder.scheduleLayout.interaction.text = sentences[0]
                startStopAction(localCounter)

                Toast.makeText(context.requireContext(), "Stopping timer", Toast.LENGTH_SHORT)
                    .show()
                localCounter = 0
            }

            buttonArray[position] = localCounter

            if (dataHelper.timerCounting()) {
                startTimer()
            } else {
                stopTimer()
                if (dataHelper.startTime() != null && dataHelper.stopTime() != null) {
                    val time = Date().time - calcRestartTime().time
                    DataList.saveTime[position] = timeStringFromLong(time)
                    resetAction()
                }
            }

            timer.scheduleAtFixedRate(TimeTask(), 0, 500)

        }

    }

    private inner class TimeTask : TimerTask() {
        override fun run() {
            if (dataHelper.timerCounting()) {
                (Date().time - dataHelper.startTime()!!.time).toString()
            }
        }
    }

    private fun resetAction() {
        dataHelper.setStopTime(null)
        dataHelper.setStartTime(null)
        stopTimer()
    }

    private fun startStopAction(localCounter: Int) {
        if (dataHelper.timerCounting() && localCounter == 1) {
            dataHelper.setStopTime(Date())
            stopTimer()
        } else {
            if (dataHelper.stopTime() != null) {
                dataHelper.setStartTime(calcRestartTime())
                dataHelper.setStopTime(null)
            } else {
                dataHelper.setStartTime(Date())
            }
            startTimer()
        }
    }

    private fun stopTimer() {
        dataHelper.setTimerCounting(false)
    }

    private fun startTimer() {
        dataHelper.setTimerCounting(true)
    }

    private fun calcRestartTime(): Date {
        val diff = dataHelper.startTime()!!.time - dataHelper.stopTime()!!.time
        return Date(System.currentTimeMillis() + diff)
    }

    override fun onViewRecycled(holder: ScheduleItemViewHolder) {
        super.onViewRecycled(holder)
        slideshowViewModel.pressed.removeObservers(this.context.viewLifecycleOwner)
    }

    private fun timeStringFromLong(ms: Long): String {
        val seconds = (ms / 1000) % 60
        val minutes = (ms / (1000 * 60) % 60)
        val hours = (ms / (1000 * 60 * 60) % 24)
        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Long, minutes: Long, seconds: Long): String {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun getItemCount() = dataset.size
}
