package com.github.mertunctuncer.ardunioserialmessager.util

import android.util.Log
import com.github.mertunctuncer.ardunioserialmessager.DEBUG

context(TagOwner)
fun info(message: String) = Log.i(tag, message)

context(TagOwner)
fun debug(message: String) {
    if(DEBUG) Log.d(tag, message)
}

context(TagOwner)
fun error(message: String) = Log.e(tag, message)

context(TagOwner)
fun warn(message: String) = Log.w(tag, message)

context(TagOwner)
fun wtf(message: String) = Log.wtf(tag, message)
