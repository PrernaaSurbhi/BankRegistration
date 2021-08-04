package com.example

import android.view.View

interface EditTextFocusListener {
    fun onFocusLostEventReceived(var1: View?)

    fun onFocusReceived(var1: View?, var2: Boolean)
}