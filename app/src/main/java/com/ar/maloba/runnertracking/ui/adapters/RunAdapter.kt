package com.ar.maloba.runnertracking.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ar.maloba.runnertracking.R
import com.ar.maloba.runnertracking.TrackingUtility
import com.ar.maloba.runnertracking.data.Run
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_run.view.*
import java.util.*

class RunAdapter : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    inner class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val diffCallback = object : DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_run,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(run.img).into(ivRunImage)
            tvDate.text = TrackingUtility.getFormattedDate(run.timestamp)
            tvAvgSpeed.text = "${run.avgSpeedInKMH}km/h"
            tvDistance.text = "${run.distanceInMeters / 1000f}km"
            tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)
            tvCalories.text = "${run.caloriesBurned}kcal"
        }
    }
}