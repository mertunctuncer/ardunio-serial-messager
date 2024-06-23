package com.github.mertunctuncer.ardunioserialmessager.util

import android.content.pm.PackageManager


context(ContextOwner)
fun hasPermission(permission: String) =
    context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED