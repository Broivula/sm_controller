package com.example.mirrorcontroller

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView



interface ElementClasses {
    //fun addToElementList(e: Element) = Database.addElementToDatabase(e)
    fun getPostableData() : String
    fun getUID() : Int
    fun getElementType() : ElementType
    fun getPosX() : Int
    fun getPosY() : Int
    fun getContent() : String?
}

enum class ElementType{
    VIDEO,
    PLAIN_TEXT,
    API_TEXT,
}


class  TextElement(val _content: String?, val _elementType: ElementType, var _posX: Int, var _posY: Int, context: Context?, private val uid : Int = Database.generateUID()): TextView(context), ElementClasses{

    override fun getUID(): Int {
        return this.uid
    }

    override fun getElementType(): ElementType { return _elementType }

    override fun getPosX(): Int { return _posX }

    override fun getPosY(): Int { return _posY }

    override fun getContent(): String? { return _content }

    override fun toString(): String {
        return """ {
            "uid": ${uid},
            |"content": "$_content",
            |"type": "$_elementType",
            |"posX": $_posX,
            |"posY": $_posY
            |} """.trimMargin()
    }

    override fun getPostableData() : String {
        return this.toString()
    }
}