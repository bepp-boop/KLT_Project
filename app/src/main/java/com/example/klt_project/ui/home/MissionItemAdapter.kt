package com.example.klt_project.ui.home

import android.content.Intent
import android.provider.ContactsContract.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klt_project.DataList
import com.example.klt_project.R

class MissionItemAdapter(
    private val context: HomeFragment,
    private val dataset: List<Missions>
) : RecyclerView.Adapter<MissionItemAdapter.MissionItemViewHolder>(){

    class MissionItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val to: TextView = view.findViewById(R.id.to)
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

        val item = dataset[position]
        holder.cityFrom.text = context.resources.getString(item.stringFromResourceId)
        holder.cityTo.text = context.resources.getString(item.stringToResourceId)
        holder.missionLayout.setOnClickListener {
            val intent = Intent(this.context.context, MissionActivity::class.java)
            this.context.activity?.startActivity(intent)
        }
    }

    override fun getItemCount() = dataset.size
}