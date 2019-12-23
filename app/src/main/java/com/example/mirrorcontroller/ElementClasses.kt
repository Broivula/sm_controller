package com.example.mirrorcontroller

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView



interface ElementClasses {

    fun getPostableData() : String
    fun getUID() : Int
    fun getElementType() : ElementType
    fun getPosX() : Float
    fun getPosY() : Float
    fun getContent() : String?
    fun updatePosition(x: Float, y: Float)
    fun setContent(content: String)
    fun addToLocalElementList(e: ElementClasses)
}

enum class ElementType{
    VIDEO,
    PLAIN_TEXT,
    API_TEXT,
}


class  TextElement(var _content: String?, val _elementType: ElementType,
                   var _posX: Float, var _posY: Float, context: Context?,
                   private val uid : Int = Database.generateUID()): TextView(context), ElementClasses{

    override fun addToLocalElementList(e: ElementClasses) { Database.addToLocalListOfElements(e)}

    override fun getUID(): Int { return this.uid }

    override fun getElementType(): ElementType { return _elementType }

    override fun getPosX(): Float { return _posX }

    override fun getPosY(): Float { return _posY }

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

    override fun updatePosition(x: Float, y: Float) {
        this._posX = x
        this._posY = y
    }

    override fun setContent(content: String) { this._content = content}

    override fun getPostableData() : String {
        return this.toString()
    }
}