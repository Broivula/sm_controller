package com.example.mirrorcontroller

interface Callback<T> {
    fun onComplete(result: T)
    fun onException(e: Exception?)
}