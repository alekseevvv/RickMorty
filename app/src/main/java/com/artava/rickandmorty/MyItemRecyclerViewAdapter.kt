package com.artava.rickandmorty

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.artava.rickandmorty.databinding.FragmentItemBinding
import com.artava.rickandmorty.model.Character
import com.squareup.picasso.Picasso

class MyItemRecyclerViewAdapter(
    private val values: List<Character>,
    private val context: Context
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

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
        if (item.status == "unknown"){
            holder.alive.setTextColor(ContextCompat.getColor(context, R.color.text_gray));
        }
        if (item.status == "Dead"){
            holder.alive.setTextColor(ContextCompat.getColor(context, R.color.text_red));
        }
        if (item.status == "Alive") {
            holder.alive.setTextColor(ContextCompat.getColor(context, R.color.text_green));
        }
        holder.alive.text = item.status
        holder.name.text = item.full_name
        holder.location.text = item.location?.name
        Picasso.get().load(item.image).into(holder.picture)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val alive: TextView = binding.txtAlive
        val name: TextView = binding.txtName
        val location: TextView = binding.txtLocation
        val picture: ImageView = binding.imPicture

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}