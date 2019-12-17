package com.example.mirrorcontroller

import android.content.Context
import androidx.room.*
import androidx.room.Database

@Entity
data class DB_Element(
    @PrimaryKey
    val uid: Int,
    var content: String?,
    val element_type: String,
    var posX: Int,
    var posY: Int
)

@Dao
interface DB_ElementDao{
    @Query("SELECT * FROM DB_Element")
    fun getAll(): MutableList<DB_Element>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(e: DB_Element)

    @Update
    fun update(e: DB_Element?)
}

@Database(entities = [(DB_Element::class)], version = 1)
abstract class ElementDatabase : RoomDatabase(){
    abstract fun DB_ElementDao(): DB_ElementDao

    companion object{
        private var sInstance: ElementDatabase? = null

        @Synchronized
        fun get(context: Context) : ElementDatabase{
            if(sInstance == null){
                sInstance = Room.databaseBuilder(context.applicationContext, ElementDatabase::class.java, "element_database").build()
            }
            return sInstance!!
        }
    }
}