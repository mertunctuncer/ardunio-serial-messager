package com.github.mertunctuncer.ardunioserialmessager.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import com.github.mertunctuncer.ardunioserialmessager.util.ContextOwner
import com.github.mertunctuncer.ardunioserialmessager.util.TagOwner
import com.github.mertunctuncer.ardunioserialmessager.util.debug
import com.github.mertunctuncer.ardunioserialmessager.util.hasPermission
import com.github.mertunctuncer.ardunioserialmessager.util.info
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BluetoothScanService(
    override val context: Context,
    private val bluetoothAdapter: BluetoothAdapter
) : ScanService, ContextOwner, TagOwner {

    override val tag: String = BluetoothScanService::class.simpleName.toString()

    private val _isScanning: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isScanning: StateFlow<Boolean> get() = _isScanning.asStateFlow()

    private val _scannedDevices: MutableStateFlow<Set<BluetoothDeviceWrapper>> = MutableStateFlow(emptySet())
    override val scannedDevices: StateFlow<Set<BluetoothDeviceWrapper>> get() = _scannedDevices.asStateFlow()

    private val _pairedDevices: MutableStateFlow<Set<BluetoothDeviceWrapper>> = MutableStateFlow(emptySet())
    override val pairedDevices: StateFlow<Set<BluetoothDeviceWrapper>> get() = _pairedDevices.asStateFlow()


    @SuppressLint("MissingPermission")
    override fun startScan() {
        if(!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) return

        if(hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            bluetoothAdapter.bondedDevices.mapTo(mutableSetOf()) { it.asBluetoothDeviceWrapper() }.also {
                devices -> _pairedDevices.update { devices }
            }
        }

        bluetoothAdapter.startDiscovery()
        _isScanning.update { true }

        info("Started scan.")
        debug("Started discovery.")
    }


    @SuppressLint("MissingPermission")
    override fun stopScan() {
        if(!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) return

        bluetoothAdapter.cancelDiscovery()
        _isScanning.update { false }
        info("Stopped scan.")
    }

    override fun close() {
        context.unregisterReceiver(scanReceiver)
        stopScan()
        info("Service closed.")
    }

    private val scanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothDevice.ACTION_FOUND != action) return

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
            }?.asBluetoothDeviceWrapper()?.let {
                info("Found device: ${it.name}")
                if (!_scannedDevices.value.contains(it))
                    _scannedDevices.update { devices -> devices + it }
            }
        }
    }

    init {
        context.registerReceiver(scanReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
    }

    companion object {
        private val TAG = this::class.simpleName
    }
}