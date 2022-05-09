package com.tut.rma

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class StringAdapter(private var movies :List<String>) : RecyclerView.Adapter<StringAdapter.TextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder
    {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.string_adapter, parent, false)
        return TextViewHolder(view)
    }
    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        println("broj="+position)
        holder.movieTitle.text=movies[position].toString()
    }
    fun updateMovies(movies: List<String>) {
        this.movies = movies
        notifyDataSetChanged()
    }
    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.textView5)
    }
}