package com.example.juanmorales11.notepad

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.juanmorales11.notepad.database.DBHelper
import com.example.juanmorales11.notepad.models.Note
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNote : AppCompatActivity() {

    private var db: DBHelper ?= null
    private var note: Note ?= null
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        guardar.setOnClickListener{

            val tittle = titulo.text.toString()
            val body = texto.text.toString()
            var newNote = Note(tittle, body)
            db = DBHelper(context)
            db!!.insertNote(newNote)

            val open = Intent(context,MainActivity::class.java)
            startActivity(open)

        }
        salir.setOnClickListener{
            val open = Intent(this,MainActivity::class.java)
            startActivity(open)
        }

    }
}
