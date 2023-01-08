package com.example.klt_project.ui.home

import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klt_project.R

class MissionItemAdapter(
    private val context: HomeFragment,
    private val dataset: List<Missions>
) : RecyclerView.Adapter<MissionItemAdapter.MissionItemViewHolder>(){

    class MissionItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val from: TextView = view.findViewById(R.id.from)
        val to: TextView = view.findViewById(R.id.to)
        //val missionName: TextView = view.findViewById(R.id.mission)
        val cityFrom: TextView = view.findViewById(R.id.city_from)
        val cityTo: TextView = view.findViewById(R.id.city_to)
        val missionLayout: LinearLayout = view.findViewById(R.id.mission_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_missions, parent, false)
        return MissionItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MissionItemViewHolder, position: Int) {
        val MISSION_MESSAGE = "com.example.klt_project.ui.home.mission.MESSAGE"
        val TO_MESSAGE = "com.example.klt_project.ui.home.to.MESSAGE"
        val FROM_MESSAGE = "com.example.klt_project.ui.home.from.MESSAGE"
        val LOAD_MESSAGE = "com.example.klt_project.ui.home.load.MESSAGE"
        val UNLOAD_MESSAGE = "com.example.klt_project.ui.home.unload.MESSAGE"
        val item = dataset[position]
        holder.cityFrom.text = context.resources.getString(item.stringFromResourceId)
        holder.cityTo.text = context.resources.getString(item.stringToResourceId)
        holder.missionLayout.setOnClickListener{
            val intent = Intent(this.context.context, MissionActivity::class.java)
            val context = context.activity
            intent.putExtra(MISSION_MESSAGE,holder.itemView.resources.getString(item.stringMissionResourceId))
            intent.putExtra(FROM_MESSAGE,holder.itemView.resources.getString(item.stringFromResourceId))
            intent.putExtra(TO_MESSAGE,holder.itemView.resources.getString(item.stringToResourceId))
            intent.putExtra(LOAD_MESSAGE,holder.itemView.resources.getString(item.stringLoadResourceId))
            intent.putExtra(UNLOAD_MESSAGE,holder.itemView.resources.getString(item.stringUnloadResourceId))
            if (context != null) {
                context.startActivity(intent)
            }
        //startActivity(context.activity as Activity,intent,1)
        }
    }

    override fun getItemCount() = dataset.size
}