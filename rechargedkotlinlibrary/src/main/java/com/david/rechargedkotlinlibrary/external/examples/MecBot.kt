package com.david.rechargedkotlinlibrary.external.examples

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.david.rechargedkotlinlibrary.internal.util.AutoTransitionerKotlin

/**
 * Created by David Lukens on 8/2/2018.
 */

class MecBot(opMode: RechargedLinearOpMode<MecBot>) : RobotTemplate(opMode, arrayOf("hub1")) {
    val drive = ExampleMecDrive(this)
    override fun autoPostInit() = AutoTransitionerKotlin.transitionOnStop(opMode, MecTele.NAME)
}