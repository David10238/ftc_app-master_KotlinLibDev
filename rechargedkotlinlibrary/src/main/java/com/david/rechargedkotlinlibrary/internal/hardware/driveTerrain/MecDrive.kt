package com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain

import com.david.rechargedkotlinlibrary.internal.hardware.devices.CachingDcMotor
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.SameThreadSubsystem
import com.qualcomm.robotcore.hardware.DcMotor

/**
 * Created by David Lukens on 8/2/2018.
 */
open class MecDrive(robot: RobotTemplate, lfConfig:String, lbConfig:String, rfConfig:String, rbConfig:String) : SameThreadSubsystem(robot){
    val lf = CachingDcMotor(robot, lfConfig)
    val lb = CachingDcMotor(robot, lbConfig)
    val rf = CachingDcMotor(robot, rfConfig)
    val rb = CachingDcMotor(robot, rbConfig)

    override fun start() {
    }
}