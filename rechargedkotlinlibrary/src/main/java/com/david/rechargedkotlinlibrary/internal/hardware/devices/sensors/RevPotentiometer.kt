package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors

/**
 * Created by David Lukens on 8/9/2018.
 */
class RevPotentiometer (configData: ConfigData){
    private val delegate = OptimumAnalogInput(configData)

    fun getDegrees() = delegate.getVoltage() * SCALER
    fun getRadians() = Math.toRadians(getDegrees())

    companion object {
        const val SCALER = 1 / 0.01222
    }
}