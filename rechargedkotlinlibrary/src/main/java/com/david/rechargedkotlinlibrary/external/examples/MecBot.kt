package com.david.rechargedkotlinlibrary.external.examples

import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.ConfigData
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.DcMotorMaker
import com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain.MecDrive
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.david.rechargedkotlinlibrary.internal.util.AutoTransitionerKotlin
import com.qualcomm.robotcore.hardware.DcMotorSimple

/**
 * Created by David Lukens on 8/2/2018.
 */

class MecBot(opMode: RechargedLinearOpMode<MecBot>) : RobotTemplate(opMode, arrayOf("hub1")) {
    val drive = MecDrive(robot = this, lf = DcMotorMaker.instantiate(ConfigData(this, 0, "lf"), direction = DcMotorSimple.Direction.REVERSE), lb = DcMotorMaker.instantiate(ConfigData(this, 0, "lb"), direction = DcMotorSimple.Direction.REVERSE), rf = DcMotorMaker.instantiate(ConfigData(this, 0, "rf")), rb = DcMotorMaker.instantiate(ConfigData(this, 0, "rb")), TRACK_WIDTH = 0.0)
    override fun autoPostInit() = AutoTransitionerKotlin.transitionOnStop(opMode, MecTele.NAME)
}