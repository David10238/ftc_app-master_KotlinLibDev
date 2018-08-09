package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.encoders

import com.david.rechargedkotlinlibrary.internal.hardware.devices.RevHub
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.util.MathUtil

/**
 * Created by David Lukens on 8/8/2018.
 */
open class Encoder (private val HUB:RevHub, private val PORT:Int, private val PPR:Int){
    private var resetTicks = 0
    init {
        reset()
    }
    fun getRawTicks() = HUB.getEncoder(PORT)
    fun reset() {
        resetTicks = getRawTicks()
    }

    fun getTicks() = getRawTicks() - resetTicks
    fun getRadians() = toRadians(getTicks())
    fun getRawRadians() = toRadians(getRawTicks())

    private fun toRadians(ticks:Int):Double{
        return ticks / PPR * MathUtil.TAU
    }
}