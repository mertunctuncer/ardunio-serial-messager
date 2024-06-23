package com.github.mertunctuncer.ardunioserialmessager.util

import android.content.Context

interface ContextOwner : TagOwner{
    val context: Context
}