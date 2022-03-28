package com.artava.rickandmorty

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artava.rickandmorty.databinding.FragmentItemBinding
import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.model.Episode
import com.squareup.picasso.Picasso

class EpisodeRecyclerViewAdapter(
    private val values: MutableList<Episode>,
) : RecyclerView.Adapter<EpisodeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
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
    }

    override fun getItemCount(): Int = values.size

    fun updateList(listCharacter: List<Episode>){
        values.addAll(listCharacter)
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val episode: TextView = binding.txtAlive
        val name: TextView = binding.txtName
        val date: TextView = binding.txtLocation

    }
}