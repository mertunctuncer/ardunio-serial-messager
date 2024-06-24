package com.github.mertunctuncer.ardunioserialmessager.model.bluetooth.connection

import com.github.mertunctuncer.ardunioserialmessager.model.bluetooth.domain.BluetoothConnection
import com.github.mertunctuncer.ardunioserialmessager.model.bluetooth.domain.BluetoothDeviceWrapper
import kotlinx.coroutines.flow.StateFlow

interface ConnectionService {

    val hasActiveConnection: StateFlow<Boolean>
    val connections: StateFlow<Set<BluetoothConnection>>

    suspend fun connect(device: BluetoothDeviceWrapper): BluetoothConnection
}