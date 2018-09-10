package org.firstinspires.ftc.teamcode.prototypes

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.david.rechargedkotlinlibrary.internal.util.AutoTransitionerKotlin

/**
 * Created by David Lukens on 9/10/2018.
 */
class EthanBot(opmode:RechargedLinearOpMode<EthanBot>) : RobotTemplate(opmode, arrayOf("hub1")){
    val drive = EthanDrive(robot = this)
    override fun autoPostInit() = AutoTransitionerKotlin.transitionOnStop(opMode, EthanTele.NAME)
}