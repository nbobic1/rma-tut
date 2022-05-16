package com.tut.rma
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.Window
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.viewpager2.adapter.FragmentViewHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.tut.rma.*

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var freg:FrameLayout
    private val br: BroadcastReceiver = Brodcast()
    private val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
    //Listener za click
    private val mOnItemSelectedListener = NavigationBarView.OnItemSelectedListener{ item ->
        when (item.itemId) {
            R.id.navigation_favorites -> {
                val favoritesFragment = FavoriteMoviesFragment.newInstance()
                openFragment(favoritesFragment)
                return@OnItemSelectedListener true
            }
            R.id.navigation_recent -> {
                println("ELESLGJSKLGJLJGLSJLSG")
                val recentFragments = RecentMoviesFragment.newInstance()
                openFragment(recentFragments)
                return@OnItemSelectedListener true
            }
            R.id.navigation_search -> {
                val searchFragment = SearchFragment.newInstance(" ")
                openFragment(searchFragment)
                return@OnItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            // postavit ćemo exitTranziciju
            exitTransition = Fade()
        }
        setContentView(R.layout.activity_mainv2)
        Intent(this, LatestMovieService::class.java).also {
//Različito pokretanje u ovisnosti od verzije
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(it)
                return
            }
            startService(it)
        }
        if(intent?.action == Intent.ACTION_SEND && intent?.type == "text/plain")
            handleSendText(intent)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        bottomNavigation = findViewById(R.id.navigationView)
        freg=findViewById(R.id.container)
        bottomNavigation.setOnItemSelectedListener(mOnItemSelectedListener)

        //Defaultni fragment
        bottomNavigation.selectedItemId= R.id.navigation_favorites
        val favoritesFragment = FavoriteMoviesFragment.newInstance()
        openFragment(favoritesFragment)
    }
    //Funkcija za izmjenu fragmenta
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
       if((freg.getChildAt(0) as ConstraintLayout).tag.toString()=="fav")
        {
            bottomNavigation.selectedItemId= R.id.navigation_favorites
        }
        else if((freg.getChildAt(0) as ConstraintLayout).tag.toString()=="rec")
           {
               bottomNavigation.selectedItemId= R.id.navigation_recent
           }
       else if((freg.getChildAt(0) as ConstraintLayout).tag.toString()=="ser")
       {
           bottomNavigation.selectedItemId= R.id.navigation_search
       }
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            bottomNavigation.selectedItemId= R.id.navigation_search
            val searchFragment = SearchFragment.newInstance(it)
            openFragment(searchFragment)
        }
    }
    override fun onResume() {
        super.onResume()
        registerReceiver(br, filter)
    }
    override fun onPause() {
        unregisterReceiver(br)
        super.onPause()
    }
}