package com.github.mertunctuncer.ardunioserialmessager.model.bluetooth

import kotlinx.coroutines.flow.StateFlow

interface ConnectionService {

    val hasActiveConnection: StateFlow<Boolean>
    val connections: StateFlow<Set<BluetoothConnection>>

    suspend fun connect(device: BluetoothDeviceWrapper): BluetoothConnection
}