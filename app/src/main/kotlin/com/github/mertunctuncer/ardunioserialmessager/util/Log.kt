package com.github.mertunctuncer.ardunioserialmessager.util

import android.util.Log
import com.github.mertunctuncer.ardunioserialmessager.DEBUG

typealias AndroidLog = Log


@Suppress("unused")
object Log {
    context(TagOwner)
    fun info(message: String, throwable: Throwable? = null) {
        if(throwable == null) AndroidLog.i(tag, message)
        else AndroidLog.i(tag, message, throwable)
    }

    context(TagOwner)
    fun debug(message: String, throwable: Throwable? = null) {
        if(!DEBUG) return

        if(throwable == null) AndroidLog.d(tag, message)
        else AndroidLog.d(tag, message, throwable)
    }

    context(TagOwner)
    fun error(message: String, throwable: Throwable? = null) {
        if(throwable == null) AndroidLog.e(tag, message)
        else AndroidLog.e(tag, message, throwable)
    }

    context(TagOwner)
    fun verbose(message: String, throwable: Throwable? = null) {
        if(throwable == null) AndroidLog.v(tag, message)
        else AndroidLog.v(tag, message, throwable)
    }

    context(TagOwner)
    fun warn(message: String, throwable: Throwable? = null) {
        if(throwable == null) AndroidLog.w(tag, message)
        else AndroidLog.w(tag, message, throwable)
    }

    context(TagOwner)
    fun wtf(message: String, throwable: Throwable? = null) {
        if(throwable == null) AndroidLog.wtf(tag, message)
        else AndroidLog.wtf(tag, message, throwable)
    }
}











