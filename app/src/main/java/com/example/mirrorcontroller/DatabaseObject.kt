package com.example.mirrorcontroller

import android.content.Context
import android.util.Log
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.doAsync
import kotlin.coroutines.coroutineContext

object Database{


    private var dataBase: ElementDatabase? = null
    private val listOfElements = mutableListOf<ElementClasses?>()

    fun checkIfDatabaseExists ()  = dataBase

    fun initDatabase(context: Context) = runBlocking{
        dataBase = ElementDatabase.get(context)
        val temp = dataBase?.DB_ElementDao()?.getAll()
        Log.d("KIKKEL", "this is our REAL db content, 4 $temp")
        listOfElements.addAll(parseToMobileElements(temp, context))
    }

    fun getListOfElements() : MutableList<ElementClasses?>{
        return listOfElements
    }

    fun generateUID () : Int {
        return listOfElements.size + 1
    }

    fun addToLocalListOfElements(e: ElementClasses){
        listOfElements.add(e)
    }

    fun addElementToDatabase(e: ElementClasses) = doAsync{
        listOfElements.add(e)
        dataBase?.DB_ElementDao()?.insert(parseToSingleDatabaseElement(e))
        Log.d("KIKKEL", "element added to database, element: ${e.getPostableData()}")
        // TODO: add the element to database
    }

    fun saveAllToDatabase() = doAsync{
        parseToDatabaseElements(listOfElements).forEach {
            dataBase?.DB_ElementDao()?.update(it)
        }
    }

    private fun parseToMobileElements(e : MutableList<DB_Element>?, context: Context) : MutableList<ElementClasses?>{
        if(e == null) return mutableListOf<ElementClasses?>()
        return e.map {
            when(it.element_type){
                "PLAIN_TEXT" ->{
                    TextElement(it.content,ElementType.valueOf(it.element_type),it.posX, it.posY, uid = it.uid, context = context)
                }
                else -> null
            }
        }.toMutableList()
    }

    private fun parseToDatabaseElements(e : MutableList<ElementClasses?>) : List<DB_Element?>{
        return e.map {
            when(it?.getElementType()){
                ElementType.PLAIN_TEXT ->{
                    DB_Element(it.getUID(), it.getContent(), it.getElementType().toString(), it.getPosX(), it.getPosY())
                }
                else -> null
            }
        }.toList()
    }

    private fun parseToSingleDatabaseElement(e: ElementClasses) : DB_Element{
        return DB_Element(
            e.getUID(),
            e.getContent(),
            e.getElementType().toString(),
            e.getPosX(),
            e.getPosY()
        )
    }
}