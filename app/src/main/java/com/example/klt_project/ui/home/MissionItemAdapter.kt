package com.example.klt_project.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_missions, parent, false)
        return MissionItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MissionItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.from.text = context.resources.getString(item.stringFromResourceId)
        holder.to.text = context.resources.getString(item.stringToResourceId)

    }

    override fun getItemCount() = dataset.size
}