package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.odometry

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d

/**
 * Created by David Lukens on 8/10/2018.
 */
interface Localizer {
    fun setPos(pos: Pose2d)
    fun getPos():Pose2d
    fun resetPos() = setPos(getPos())
}