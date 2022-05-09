package com.tut.rma

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment() {
    private lateinit var searchText: EditText
    private lateinit var dugme: ImageButton
    private lateinit var rec:RecyclerView
    var movielistViewModel=MovieListViewModel(this@SearchFragment::searchDone,this@SearchFragment::onError)
    private lateinit var ada:MovieListAdapter
    private fun onClick() {
        val toast = Toast.makeText(context, "Search start", Toast.LENGTH_SHORT)
        toast.show()
        movielistViewModel.search(searchText.text.toString())
    }
    fun searchDone(movies:List<Movie>){
        val toast = Toast.makeText(context, "Search done", Toast.LENGTH_SHORT)
        toast.show()
        ada.updateMovies(movies)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }
    private fun showMovieDetails(movie: Movie, view1: View,view2:View) {
        val intent = Intent(activity, MainActivity2::class.java).apply {
            putExtra("movie", movie)
        }
        val options = ActivityOptions
            .makeSceneTransitionAnimation(activity,  Pair.create(view1, "poster"),
                Pair.create(view2, "title"))
        this.context?.let { ContextCompat.startActivity(it,intent, options.toBundle()) }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.search_fragment, container, false)
        searchText = view.findViewById(R.id.searchText)
        arguments?.getString("search")?.let {
            searchText.setText(it)
        dugme=view.findViewById(R.id.searchButton)
            dugme.setOnClickListener {
                val toast = Toast.makeText(context, "Search start", Toast.LENGTH_SHORT)
                toast.show()
                movielistViewModel.search(searchText.text.toString())
            }
            rec=view.findViewById(R.id.searchMovies)
            rec.layoutManager = GridLayoutManager(activity, 2)
            ada=MovieListAdapter(listOf(),true) { movie,view,view2 -> showMovieDetails(movie,view,view2) }
            rec.adapter=ada
        }
        return view;
    }
    companion object {
        fun newInstance(search:String): SearchFragment = SearchFragment().apply {
            arguments = Bundle().apply {
                putString("search", search)
            }
        }

    }
}