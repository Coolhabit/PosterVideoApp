package com.coolhabit.postervideoapp.video.presentation.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


class CustomLongClickListener() : View.OnLongClickListener {

    override fun onLongClick(view: View): Boolean {
        view.requestFocus()
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        return true
    }
}
