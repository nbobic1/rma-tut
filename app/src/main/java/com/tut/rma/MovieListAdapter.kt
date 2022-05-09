package com.tut.rma

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieListAdapter (private var movies :List<Movie>,private var vrsta:Boolean,  private val onItemClicked: (movie:Movie,view:View,view2:View) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private val posterPath = "https://image.tmdb.org/t/p/w342"
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

        val genreMatch: String= movies[position].genre.toString()

//Pronalazimo id drawable elementa na osnovu naziva žanra
        val context: Context = holder.movieImage.getContext()
        var id: Int = context.getResources()
            .getIdentifier(genreMatch, "drawable", context.getPackageName())
        if (id===0) id=context.getResources()
            .getIdentifier("b", "drawable", context.getPackageName())
        holder.movieImage.setImageResource(id)

if(vrsta)
{
        val genreMatch1: String? = movies[position].genre
        val context1: Context = holder.movieImage.getContext()
        var id1: Int = 0;
        if (genreMatch!==null)
            id = context.getResources()
                .getIdentifier(genreMatch, "drawable", context.getPackageName())
        if (id===0) id=context.getResources()
            .getIdentifier("picture1", "drawable", context.getPackageName())
        Glide.with(context1)
            .load(posterPath + movies[position].posterPath)
            .centerCrop()
            .placeholder(R.drawable.b)
            .error(id1)
            .fallback(id1)
            .into(holder.movieImage);

        }
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