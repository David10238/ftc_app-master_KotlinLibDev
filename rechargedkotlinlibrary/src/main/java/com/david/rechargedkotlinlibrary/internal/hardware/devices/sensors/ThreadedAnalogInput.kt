package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem
import com.qualcomm.robotcore.hardware.AnalogInput
import com.qualcomm.robotcore.hardware.AnalogSensor

/**
 * Created by David Lukens on 8/3/2018.
 */
class ThreadedAnalogInput(robot: RobotTemplate, config: String) : ThreadedSubsystem(robot), AnalogSensor {
    private val delegate = hMap.get(AnalogInput::class.java, config)

    private var v: Double = 0.0
    private val maxV = delegate.maxVoltage

    private var frozenRead = false
    fun getFrozenRead() = frozenRead
    fun setFrozenRead(value:Boolean){
        frozenRead = value
    }

    override fun start() {
    }

    override fun update() {
        if(!frozenRead)
            v = delegate.voltage
    }

    override fun readRawVoltage(): Double = v
    fun getMaxVoltage():Double = maxV
}