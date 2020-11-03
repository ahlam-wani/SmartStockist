package com.smartstockist.app.utils

import android.content.Context
import android.media.session.MediaSession
class SessionManager(private val context:Context) {
    fun setSession(session:Session):Boolean{
        val(token)=session
        return setToken(token)
    }
    fun getSession():Session?{
        val token=getToken()?:return null
        return Session(token)
    }
     fun setToken(token:String):Boolean{
        val preferences=context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
         val editor=preferences.edit()
         editor.putString(KEY_TOKEN,token)
         editor.apply()
         return editor.commit()

    }
    fun getToken():String?=context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).getString(KEY_TOKEN,"")
    companion object{
        private const val KEY_TOKEN="token"

    }
}
data class Session(val token:String)