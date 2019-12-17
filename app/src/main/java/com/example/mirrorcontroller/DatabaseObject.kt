package com.example.mirrorcontroller

import android.content.Context

object Database{


    private lateinit var dataBase: ElementDatabase
    private val listOfElements = mutableListOf<ElementClasses?>()

    fun initDatabase(context: Context){
        dataBase = ElementDatabase.get(context)
        val temp = dataBase.DB_ElementDao().getAll()
        listOfElements.addAll(parseToMobileElements(temp))
    }

    fun getListOfElements() : MutableList<ElementClasses?>{
        return listOfElements
    }

    fun generateUID () : Int {
        return listOfElements.size + 1
    }

    fun addElementToDatabase(e: ElementClasses){
        listOfElements.add(e)
        dataBase.DB_ElementDao().insert(parseToSingleDatabaseElement(e))
        // TODO: add the element to database
    }

    fun saveAllToDatabase(){
        parseToDatabaseElements(listOfElements).forEach {
            dataBase.DB_ElementDao().update(it)
        }
    }

    fun parseToMobileElements(e : MutableList<DB_Element>?) : MutableList<ElementClasses?>{
        if(e == null) return mutableListOf<ElementClasses?>()
        return e.map {
            when(it.element_type){
                "PLAIN_TEXT" ->{
                    TextElement(it.content,ElementType.valueOf(it.element_type),it.posX, it.posY, uid = it.uid, context = null)
                }
                else -> null
            }
        }.toMutableList()
    }

    fun parseToDatabaseElements(e : MutableList<ElementClasses?>) : List<DB_Element?>{
        return e.map {
            when(it?.getElementType()){
                ElementType.PLAIN_TEXT ->{
                    DB_Element(it.getUID(), it.getContent(), it.getElementType().toString(), it.getPosX(), it.getPosY())
                }
                else -> null
            }
        }.toList()
    }

    fun parseToSingleDatabaseElement(e: ElementClasses) : DB_Element{
        return DB_Element(
            e.getUID(),
            e.getContent(),
            e.getElementType().toString(),
            e.getPosX(),
            e.getPosY()
        )
    }
}