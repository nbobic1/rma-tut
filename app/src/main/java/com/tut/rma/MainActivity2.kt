package com.tut.rma

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

class MainActivity2 : AppCompatActivity() {
    private var movieDetailViewModel =  MovieDetailViewModel()
    private lateinit var movie: Movie
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var share : FloatingActionButton
    private lateinit var poster : ImageView
    private lateinit var budon: BottomNavigationView
    private lateinit var frego: FragmentContainerView
    private val mOnItemSelectedListener = NavigationBarView.OnItemSelectedListener{ item ->
        when (item.itemId) {
            R.id.accters -> {
                val favoritesFragment = MainActivity2Fregment.newInstance(movieDetailViewModel.getMovieByTitle(title.text.toString()).accter)
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView2, favoritesFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                return@OnItemSelectedListener true
            }
            R.id.similar -> {
                val favoritesFragment = MainActivity2Fregment.newInstance(movieDetailViewModel.getMovieByTitle(title.text.toString()).similar)
                 val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView2, favoritesFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                return@OnItemSelectedListener true
            }

        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        budon=findViewById(R.id.dugmad)
        frego=findViewById(R.id.fragmentContainerView2)
        budon.setOnItemSelectedListener(mOnItemSelectedListener)
        title = findViewById(R.id.movie_title)
        overview = findViewById(R.id.movie_overview)
        releaseDate = findViewById(R.id.movie_release_date)
        genre = findViewById(R.id.movie_genre)
        poster = findViewById(R.id.movie_poster)
        website = findViewById(R.id.movie_website)
        share=findViewById(R.id.shareButton)
        share.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, movie.overview)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        title.setOnClickListener{
            val webIntent: Intent = Uri.parse("https://www.youtube.com/results?search_query="+movie.title).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            try {
                startActivity(webIntent)
            } catch (e: ActivityNotFoundException) {
                // Definisati naredbe ako ne postoji aplikacija za navedenu akciju
            }
        }
        website.setOnClickListener{
            showWebsite()
        }
        val extras = intent.extras
        if (extras != null) {
            movie = movieDetailViewModel.getMovieByTitle(extras.getString("movie_title",""))
            populateDetails()
        } else {
            finish()
        }
    }
    private fun showWebsite(){
        val webIntent: Intent = Uri.parse(movie.homepage).let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        try {
            startActivity(webIntent)
        } catch (e: ActivityNotFoundException) {
            // Definisati naredbe ako ne postoji aplikacija za navedenu akciju
        }
    }
    private fun populateDetails() {
//        System.out.println("rilffadalsfaljgldajglajgl");
        title.text=movie.title
        releaseDate.text=movie.releaseDate
        genre.text=movie.genre
        website.text=movie.homepage
        overview.text=movie.overview
        val context: Context = poster.context
        var id: Int = context.resources
            .getIdentifier(movie.genre.substring(0,1), "drawable", context.packageName)
        if (id===0) id=context.resources
            .getIdentifier("picture1", "drawable", context.packageName)
        poster.setImageResource(id)
    }

}