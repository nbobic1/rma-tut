package com.tut.rma

import android.content.Context
import android.icu.util.RangeValueIterator
import android.os.Parcel
import android.os.Parcelable
import android.sax.Element
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes

class MyArrayAdapter(context: Context, @LayoutRes private val layoutResource: Int,
private val elements: List<String>):
ArrayAdapter<String>(context, layoutResource, elements) {

    override fun getView(position: Int, newView: View?, parent: ViewGroup): View {
        var newView = newView
//Vrši se inflate layout-a odnosno kreira se odgovarajući view
//svakog elementa na osnovu postojećeg layout-a

        newView = LayoutInflater.from(context).inflate(R.layout.recycler_view_adapter, parent,
            false)
// Ovdje možete dohvatiti reference na View
        val textView = newView.findViewById<TextView>(R.id.textView4)
        val element = elements.get(position)
        textView.text=element
        return newView
    }

}