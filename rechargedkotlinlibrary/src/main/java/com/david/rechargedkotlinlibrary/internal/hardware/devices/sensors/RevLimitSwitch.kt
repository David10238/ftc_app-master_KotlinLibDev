package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors

import com.david.rechargedkotlinlibrary.internal.hardware.devices.RevHub

/**
 * Created by David Lukens on 8/9/2018.
 */
class RevLimitSwitch(HUB: RevHub, PORT: Int) {
    private val delegate = OptimumDigitalInput(HUB, PORT)
    fun near() = !far()
    fun far() = delegate.state()
}