package com.example.juanmorales11.notepad.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.juanmorales11.notepad.models.Note

val DATABASE_NAME = "Notes"
val DATABASE_VERSION = 1

class DBHelper (var context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val queryCreateTable = "CREATE TABLE "+ Tables.Notes.TABLE_NAME +
                " ("+ Tables.Notes._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Tables.Notes.COLUMN_TITLE + " TEXT NOT NULL,"+
                Tables.Notes.COLUMN_BODY+ " TEXT NOT NULL)";
        p0!!.execSQL(queryCreateTable)
    }

    fun insertNote(note: Note){
        val db = this.writableDatabase
        val datos = ContentValues()

        datos.put(Tables.Notes.COLUMN_TITLE, note.getTitle())
        datos.put(Tables.Notes.COLUMN_BODY, note.getBody())

        var resultado = db!!.insert(Tables.Notes.TABLE_NAME,null,datos)

        if(resultado === -1.toLong())
        {
            Toast.makeText(context,"Hubo un error al ingresar la nota",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Nota creada con éxito",Toast.LENGTH_SHORT).show()
        }
    }

    fun getNotes() : MutableList<Note> {

        var notes : MutableList<Note> = ArrayList()
        val db = this.readableDatabase
        val querySelect = "SELECT * FROM " + Tables.Notes.TABLE_NAME
        var resultado = db!!.rawQuery(querySelect,null)

        if(resultado.moveToFirst())
        {
            do {
                var note = Note()
                note.setID(resultado.getString(resultado
                        .getColumnIndex(Tables.Notes._ID)).toInt())

                note.setBody(resultado.getString(resultado
                        .getColumnIndex(Tables.Notes.COLUMN_BODY)))

                note.setTitle(resultado.getString(resultado
                        .getColumnIndex(Tables.Notes.COLUMN_TITLE)))

                notes.add(note)
            }while (resultado.moveToNext())
        }

        resultado.close()
        db!!.close()
        return notes
    }

}
