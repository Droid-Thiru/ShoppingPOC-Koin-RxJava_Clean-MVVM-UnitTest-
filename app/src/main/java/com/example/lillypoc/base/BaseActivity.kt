package com.example.lillypoc.base

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(){
    private val progressDialogue by lazy { CustomProgressDialogue(this) }

    protected fun showProgress(){
        if(!progressDialogue.isShowing){
            progressDialogue.show()
        }
    }

    protected fun dismissProgress(){
       if(progressDialogue.isShowing){
           progressDialogue.dismiss()
       }
    }
}