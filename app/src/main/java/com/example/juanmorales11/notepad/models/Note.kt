package com.example.juanmorales11.notepad.models

import android.util.Log

class Note {

    private var title: String = ""
    private var body:String = ""
    private var id : Int = 0

    constructor(title: String, body: String){
        this.title = title
        this.body = body
    }

    constructor()
    {}

    fun getTitle():String{
        return this.title
    }

    fun getBody(): String{
        return this.body
    }

    fun getID() : Int
    {
        return this.id
    }

    fun setTitle(title : String)
    {
        this.title  = title
    }

    fun setBody(body : String)
    {
        this.body = body
    }

    fun setID(id : Int)
    {
        this.id = id
    }

    override fun toString(): String {
        return "${this.title}"
    }
}