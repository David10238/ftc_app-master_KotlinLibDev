package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.util.MathUtil

/**
 * Created by David Lukens on 8/8/2018.
 */
class Encoder (robot:RobotTemplate, hub:Int, private val PORT:Int, private val CPR:Int = 1120){
    private val HUB = robot.getHub(hub)

    private var resetTicks = 0
    fun getRawTicks() = HUB.getEncoder(PORT)
    fun reset() {
        resetTicks = getRawTicks()
    }

    fun getTicks() = getRawTicks() - resetTicks
    fun getRadians() = toRadians(getTicks())
    fun getRawRadians() = toRadians(getRawTicks())

    private fun toRadians(ticks:Int):Double{
        return ticks / CPR * MathUtil.TAU
    }
}