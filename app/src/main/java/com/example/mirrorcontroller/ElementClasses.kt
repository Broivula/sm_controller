package com.example.mirrorcontroller

abstract class Element(val uid: Int, val content: String?, val elementType: ElementType, val posX: Int, val posY: Int): ElementClasses

interface ElementClasses {
    fun addToElementList(e: Element) = Database.addElementToDatabase(e)
    fun getPostableData() : String
}

enum class ElementType(){
    VIDEO,
    PLAIN_TEXT,
    API_TEXT,
}


class  TextElement(content: String?, elementType: ElementType, posX: Int, posY: Int): Element(Database.generateUID(), content, elementType, posX, posY) {
    init {
        super.addToElementList(this)
    }


    override fun toString(): String {
        return """ {
            "uid": ${super.uid},
            |"content": "$content",
            |"type": "$elementType",
            |"posX": $posX,
            |"posY": $posY
            |} """.trimMargin()
    }

    override fun getPostableData() : String {
        return this.toString()
    }
}