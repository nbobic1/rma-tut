package com.tut.rma

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieListAdapter (private var movies :List<Movie>,  private val onItemClicked: (movie:Movie,view:View,view2:View) -> Unit) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder
    {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_view_adapter, parent, false)
        return MovieViewHolder(view)
    }
    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title
        holder.itemView.setOnClickListener{ onItemClicked(movies[position],holder.movieImage,holder.movieTitle) }
        val genreMatch: String = movies[position].genre
//Pronalazimo id drawable elementa na osnovu naziva žanra
        val context: Context = holder.movieImage.getContext()
        var id: Int = context.getResources()
            .getIdentifier(genreMatch, "drawable", context.getPackageName())
        if (id===0) id=context.getResources()
            .getIdentifier("b", "drawable", context.getPackageName())
        holder.movieImage.setImageResource(id)
    }
    fun updateMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.imageView2)
        val movieTitle: TextView = itemView.findViewById(R.id.textView4)
    }
}