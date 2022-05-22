package com.example.lillypoc.base

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.example.lillypoc.R


class CustomProgressDialogue(context: Context) : Dialog(context) {

    init {
        val windowAtt: WindowManager.LayoutParams? = window?.attributes
        windowAtt?.gravity = Gravity.CENTER_HORIZONTAL
        window?.attributes = windowAtt
        setCancelable(false)
        setOnCancelListener(null)
        val view: View = LayoutInflater.from(context).inflate(R.layout.custom_progress, null)
        setContentView(view)
    }
}