package com.github.mertunctuncer.ardunioserialmessager.model.bluetooth

import kotlinx.coroutines.flow.StateFlow

interface BluetoothConnection {

    val receivedMessages: StateFlow<List<BluetoothMessage>>

    fun sendMessage(message: BluetoothMessage)
}