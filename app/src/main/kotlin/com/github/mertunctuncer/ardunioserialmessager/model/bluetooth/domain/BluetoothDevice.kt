package com.github.mertunctuncer.ardunioserialmessager.model.bluetooth.domain

import android.annotation.SuppressLint


typealias BluetoothDeviceWrapper = BluetoothDevice
data class BluetoothDevice(
    val name: String?,
    val address: String
)


@SuppressLint("MissingPermission")
fun android.bluetooth.BluetoothDevice.asBluetoothDeviceWrapper() = BluetoothDeviceWrapper(this.name, this.address)
