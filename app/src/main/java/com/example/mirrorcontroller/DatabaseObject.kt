package com.example.mirrorcontroller

import android.content.Context

object Database{


    private lateinit var dataBase: ElementDatabase
    private val listOfElements = mutableListOf<Element?>()

    fun initDatabase(context: Context){
        dataBase = ElementDatabase.get(context)
        val temp = dataBase.DB_ElementDao().getAll()
        listOfElements.addAll(parseToMobileElements(temp))
    }

    fun getListOfElements() : MutableList<Element?>{
        return listOfElements
    }

    fun generateUID () : Int {
        return listOfElements.size + 1
    }

    fun addElementToDatabase(e: Element){
        listOfElements.add(e)
        dataBase.DB_ElementDao().insert(parseToSingleDatabaseElement(e))
        // TODO: add the element to database
    }

    fun saveAllToDatabase(){
        parseToDatabaseElements(listOfElements).forEach {
            dataBase.DB_ElementDao().update(it)
        }
    }

    fun parseToMobileElements(e : MutableList<DB_Element>?) : MutableList<Element?>{
        if(e == null) return mutableListOf<Element?>()
        return e.map {
            when(it.element_type){
                "PLAIN_TEXT" ->{
                    TextElement(it.content,ElementType.valueOf(it.element_type),it.posX, it.posY)
                }
                else -> null
            }
        }.toMutableList()
    }

    fun parseToDatabaseElements(e : MutableList<Element?>) : List<DB_Element?>{
        return e.map {
            when(it?.elementType){
                ElementType.PLAIN_TEXT ->{
                    DB_Element(it.uid, it.content, it.elementType.toString(), it.posX, it.posY )
                }
                else -> null
            }
        }.toList()
    }

    fun parseToSingleDatabaseElement(e: Element) : DB_Element{
        return DB_Element(
            e.uid,
            e.content,
            e.elementType.toString(),
            e.posX,
            e.posY
        )
    }
}