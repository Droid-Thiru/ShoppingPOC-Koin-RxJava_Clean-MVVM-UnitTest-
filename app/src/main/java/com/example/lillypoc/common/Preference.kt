package com.example.lillypoc.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


object Preference {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    fun initialize(context: Context){
        sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    fun addPref(key:String,value:Any){
        if(editor != null){
            when (value) {
                is String -> editor?.putString(key,value)
                is Int -> editor?.putInt(key,value)
                is Long -> editor?.putLong(key,value)
                is Boolean -> editor?.putBoolean(key,value)
                is Float -> editor?.putFloat(key,value)
            }
            editor?.commit()
        }else{
            throw Throwable("Must initialize the Preference in Application class")
        }
    }

    fun getValue(key: String, default:Any):Any?{
        if(sharedPreferences != null){
            return when (default) {
                is String -> sharedPreferences?.getString(key,default)
                is Int -> sharedPreferences?.getInt(key,default)
                is Long -> sharedPreferences?.getLong(key,default)
                is Boolean -> sharedPreferences?.getBoolean(key,default)
                is Float -> sharedPreferences?.getFloat(key,default)
                else ->{null}
            }
        }else{
            throw Throwable("Must initialize the Preference in Application class")
        }
    }
}