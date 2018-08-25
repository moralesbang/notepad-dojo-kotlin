package com.example.juanmorales11.notepad

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import com.example.juanmorales11.notepad.database.DBHelper
import com.example.juanmorales11.notepad.models.Note

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    override fun onItemLongClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    internal var lvsLista: ListView ?= null
    var notes : MutableList<Note> = ArrayList()
    private var vibrator : Vibrator ?= null
    private var db: DBHelper ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator ?
        lvsLista = findViewById(R.id.lista)

        lvsLista!!.onItemClickListener = this
        lvsLista!!.onItemLongClickListener = this

        fab.setOnClickListener {
            val open = Intent(this,NewNote::class.java)
            startActivity(open)
        }

        val context = this
        db = DBHelper(context)
        notes= db!!.getNotes()
        refrescar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun refrescar()
    {
        notes = db!!.getNotes()
        var adapter = ArrayAdapter<Note>(this,android.R.layout.simple_list_item_1,notes)
        lvsLista!!.adapter= adapter as ListAdapter?
    }
}
