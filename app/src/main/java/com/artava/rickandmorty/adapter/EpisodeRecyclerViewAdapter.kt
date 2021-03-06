package com.artava.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artava.rickandmorty.R
import com.artava.rickandmorty.databinding.EpisodeItemBinding
import com.artava.rickandmorty.model.Episode

class EpisodeRecyclerViewAdapter(
    private val values: MutableList<Episode>,
) : RecyclerView.Adapter<EpisodeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            EpisodeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.episode.text = item.episode
        holder.name.text = item.name
        holder.date.text = item.air_date
        holder.imgage.setImageResource(R.drawable.chapter)
    }

    override fun getItemCount(): Int = values.size

    fun updateList(listCharacter: List<Episode>){
        values.addAll(listCharacter)
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: EpisodeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val episode: TextView = binding.txtEpisodeName
        val name: TextView = binding.txtEpisode
        val date: TextView = binding.txtDate
        val imgage: ImageView = binding.imPicture

    }
}