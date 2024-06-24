package com.github.mertunctuncer.ardunioserialmessager.model.bluetooth.domain

import kotlinx.coroutines.flow.StateFlow

interface BluetoothConnection {

    val receivedMessages: StateFlow<List<BluetoothMessage>>

    fun sendMessage(message: BluetoothMessage)
}