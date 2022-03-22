package com.tut.rma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var editText :EditText
    private lateinit var button :Button
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val listaVrjednosti = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText=findViewById(R.id.editText1)
        button=findViewById(R.id.button1)
        listView=findViewById(R.id.listView1)
        adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,listaVrjednosti)
        listView.adapter=adapter
        button.setOnClickListener{
            addEle()
        }
    }
    private fun addEle()
    {
        listaVrjednosti.add(0,editText.text.toString())
        adapter.notifyDataSetChanged()
        editText.setText("")
    }
}