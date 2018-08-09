package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors

import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem

/**
 * Created by David Lukens on 8/9/2018.
 */
class OptimumAnalogInput(configData: ConfigData) : ThreadedSubsystem(configData.robot){
    val delegate = configData.robot.hMap.analogInput.get(configData.config)

    private val MAX_VOLTAGE = delegate.maxVoltage
    private var voltage = 0.0

    override fun update() {
        voltage = delegate.voltage
    }

    fun getVoltage() = voltage
    fun getMaxVoltage() = MAX_VOLTAGE
}