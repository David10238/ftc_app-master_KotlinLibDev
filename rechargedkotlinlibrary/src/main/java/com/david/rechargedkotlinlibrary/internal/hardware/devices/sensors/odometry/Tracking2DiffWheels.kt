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
class Tracking2DiffWheels(robot: RobotTemplate, var RADIUS:Double, var TICK_SCALER:Double = 1.0, var L: Encoder, var R:Encoder, val TRACK_WIDTH:Double, private var pos:Pose2d = Pose2d(Vector2d(0.0, 0.0), 0.0)) : ThreadedSubsystem(robot){

    override fun update() {
        val dl = L.radiansChange()
        val dr = R.radiansChange()

        if(dl != null && dr != null) {
            val dc = (dl + dr) / 2
            var heading = pos.heading
            pos += Pose2d(Vector2d(x = dc * Math.cos(heading), y = dc * Math.sin(heading)), heading = (dr - dl) / TRACK_WIDTH)
        }
    }

    fun getPos() = pos

    fun ticksToRadians(radians:Double) = MathUtil.radiansToInches(radians * TICK_SCALER, RADIUS)

    fun setPos(pos:Pose2d) {
        this.pos = pos
    }
}