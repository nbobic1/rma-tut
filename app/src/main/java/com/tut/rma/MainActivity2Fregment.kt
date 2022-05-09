package com.tut.rma

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import android.widget.ArrayAdapter as ArrayAdapter

class MainActivity2Fregment(): Fragment() {
    private lateinit var favoriteMovies: RecyclerView
  var utt=StringAdapter(listOf())
    private var lizu:List<String> =listOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.main2_fragment, container, false)
        favoriteMovies = view.findViewById(R.id.lista)
        favoriteMovies.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        favoriteMovies.adapter=utt
        utt.updateMovies(lizu)
        return view
    }
    companion object {
        fun newInstance(zu:List<String>): MainActivity2Fregment {
            var t=MainActivity2Fregment()
            t.lizu=zu
            return t
        }
    }
    fun proijeni(lis:List<String>){
        utt.updateMovies(lis)
    }

}