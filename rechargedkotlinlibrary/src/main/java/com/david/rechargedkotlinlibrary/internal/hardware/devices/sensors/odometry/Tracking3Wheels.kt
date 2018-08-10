package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.odometry

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.encoders.Encoder
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem
import com.david.rechargedkotlinlibrary.internal.util.MathUtil

/**
 * Created by David Lukens on 8/8/2018.
 */
class Tracking3Wheels(robot: RobotTemplate,
                      private val RADIUS: Double,
                      private val LEFT_WHEEL: Encoder,
                      private val RIGHT_WHEEL: Encoder,
                      private val BACK_WHEEL: Encoder,
                      private val TRACK_WIDTH: Double,
                      private val BACK_OFFSET: Double,
                      private val TICK_SCALER: Double = 1.0,
                      private var pos: Pose2d = Pose2d(Vector2d(0.0, 0.0), 0.0)) :
        ThreadedSubsystem(robot) {

    override fun update() {
        val leftChange = LEFT_WHEEL.radiansChange()
        val rightChange = RIGHT_WHEEL.radiansChange()
        val backChange = BACK_WHEEL.radiansChange()
        if(leftChange != null && rightChange != null && backChange != null) {
            val e0 = radiansToInches(leftChange)
            val e1 = radiansToInches(rightChange)
            val e2 = radiansToInches(backChange)
            val dx = (e0 + e1) / 2
            val dy = ((BACK_OFFSET * (e0 - e1)) / TRACK_WIDTH) + e2
            val dφ = (e1 - e0) / TRACK_WIDTH
            pos += Pose2d(Vector2d(dx, dy), dφ) // I need to turn this from robot centric to field centric
        }
    }

    private fun radiansToInches(radians: Double) = MathUtil.radiansToInches(radians * TICK_SCALER, RADIUS)

    private fun setPos(pos:Pose2d) {
        this.pos = pos
    }
    private fun getPos() = pos
}