package com.david.rechargedkotlinlibrary.external.examples

import com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain.MecDrive
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.david.rechargedkotlinlibrary.internal.util.AutoTransitionerKotlin

/**
 * Created by David Lukens on 8/2/2018.
 */

class MecBot(opMode: RechargedLinearOpMode<MecBot>) : RobotTemplate(opMode, arrayOf("hub1")) {
    val drive = MecDrive(robot = this, lf = ThreadedDcMotorEx(robot = this, config = "lf", hub = 0), lb = ThreadedDcMotorEx(robot = this, config = "lb", hub = 0), rf = ThreadedDcMotorEx(robot = this, config = "rf", hub = 0), rb = ThreadedDcMotorEx(robot = this, config = "rb", hub = 0), BASE_WIDTH = 0.0)
    override fun autoPostInit() = AutoTransitionerKotlin.transitionOnStop(opMode, MecTele.NAME)
}