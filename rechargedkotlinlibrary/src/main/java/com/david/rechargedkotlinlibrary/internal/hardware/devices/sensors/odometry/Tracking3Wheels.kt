package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.odometry

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.encoders.Encoder
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem

/**
 * Created by David Lukens on 8/8/2018.
 */
class Tracking3Wheels(robot: RobotTemplate, private val RADIUS:Double, private val LEFT_WHEEL: Encoder, private val RIGHT_WHEEL: Encoder, private val THIRD_WHEEL: Encoder, private val PARALLEL_WHEELS_CENTER_OFFSET:Double, private val THIRD_WHEEL_CENTER_OFFSET:Double, private val TICK_SCALER:Double = 1.0, private var pos:Pose2d = Pose2d(Vector2d(0.0, 0.0), 0.0)) : ThreadedSubsystem(robot){

    var lastW0 = radiansToInches(LEFT_WHEEL.getRadians())
    var lastW1 = radiansToInches(RIGHT_WHEEL.getRadians())
    var lastW2 = radiansToInches(THIRD_WHEEL.getRadians())

    override fun update() {
        val w0 = radiansToInches(LEFT_WHEEL.getRawRadians())
        val w1 = radiansToInches(RIGHT_WHEEL.getRadians())
        val w2 = radiansToInches(THIRD_WHEEL.getRadians())

        val e0 = w0 - lastW0
        val e1 = w0 - lastW1
        val e2 = w0 - lastW2

        val xChange = (e0 + e1) / (2)
        val yChange = ((PARALLEL_WHEELS_CENTER_OFFSET * (e0 - e1)) / (2 * THIRD_WHEEL_CENTER_OFFSET)) + e2
        val deltaChange = (e1 - e0) / (2 * THIRD_WHEEL_CENTER_OFFSET)


        lastW0 = w0
        lastW1 = w1
        lastW2 = w2
    }

    private fun radiansToInches(radians:Double) = radians * RADIUS * TICK_SCALER

    private fun getPos() = pos
}