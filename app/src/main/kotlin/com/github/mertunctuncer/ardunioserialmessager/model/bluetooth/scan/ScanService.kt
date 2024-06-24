package com.github.mertunctuncer.ardunioserialmessager.model.bluetooth.scan

import com.github.mertunctuncer.ardunioserialmessager.model.bluetooth.domain.BluetoothDeviceWrapper
import kotlinx.coroutines.flow.StateFlow

interface ScanService: AutoCloseable {

    val isScanning: StateFlow<Boolean>
    val scannedDevices: StateFlow<Set<BluetoothDeviceWrapper>>
    val pairedDevices: StateFlow<Set<BluetoothDeviceWrapper>>

    fun startScan()
    fun stopScan()
}