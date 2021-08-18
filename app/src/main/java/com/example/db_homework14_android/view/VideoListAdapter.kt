package com.example.db_homework14_android.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.db_homework14_android.POSTER_URL
import com.example.db_homework14_android.R
import com.example.db_homework14_android.data.VideoRoom

class VideoListAdapter(private val activity: Activity) : RecyclerView.Adapter<VideoViewHolder>() {

    var results: List<VideoRoom> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val item: View = activity.layoutInflater.inflate(R.layout.item, parent, false)
        return VideoViewHolder(item)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val result: VideoRoom = results[position]
        val vTitle = holder.itemView.findViewById<TextView>(R.id.videoTitle)
        val vOverview = holder.itemView.findViewById<TextView>(R.id.videoOverview)
        val vAvg = holder.itemView.findViewById<TextView>(R.id.videoVoteAvg)
        val vImage = holder.itemView.findViewById<ImageView>(R.id.Image)

        vTitle.text = result.title
        vOverview.text = result.overview
        vAvg.text = result.vote_average.toString()
        Glide
            .with(holder.itemView.context)
            .load(POSTER_URL+result.poster_path)
            .thumbnail(Glide.with(activity).load(R.drawable.no_image))
            .into(vImage)
    }

    override fun getItemCount() = results.size

}

