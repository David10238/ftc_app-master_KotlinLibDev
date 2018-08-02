package com.david.rechargedkotlinlibrary.external.examples

import com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain.MecDrive
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.david.rechargedkotlinlibrary.internal.util.AutoTransitioner

/**
 * Created by David Lukens on 8/2/2018.
 */

class MecBot(opMode: RechargedLinearOpMode<MecBot>):RobotTemplate(opMode){
    val drive = MecDrive(robot = this, lfConfig = "lf", lbConfig = "lb", rfConfig = "rf", rbConfig = "rb")
    override fun autoPostInit() = AutoTransitioner.transitionOnStop(opMode, MecTele.NAME)
}