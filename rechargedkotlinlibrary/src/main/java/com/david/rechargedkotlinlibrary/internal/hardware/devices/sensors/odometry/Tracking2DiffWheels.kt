package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.odometry

import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.encoders.Encoder
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem
import com.david.rechargedkotlinlibrary.internal.util.MathUtil

/**
 * Created by David Lukens on 8/8/2018.
 */
class Tracking2DiffWheels(robot: RobotTemplate, var RADIUS:Double, var TICK_SCALER:Double = 1.0, var L: Encoder, var R:Encoder, val TRACK_WIDTH:Double) : ThreadedSubsystem(robot){
    private var x = 0.0
    private var y = 0.0
    private var delta = 0.0

    private var lastLPos = ticksToRadians(L.getRawRadians())
    private var lastRPos = ticksToRadians(R.getRawRadians())

    override fun update() {
        val lPos = ticksToRadians(L.getRawRadians())
        val rPos = ticksToRadians(R.getRawRadians())

        val de = lPos - lastLPos
        val dr = rPos - lastRPos

        val dc = (de + dr) / 2

        x += dc * Math.cos(delta)
        y += dc * Math.sin(delta)
        delta += (dr - de) / TRACK_WIDTH

        lastLPos = lPos
        lastRPos = rPos
    }

    fun ticksToRadians(radians:Double) = RADIUS * TICK_SCALER * radians
}