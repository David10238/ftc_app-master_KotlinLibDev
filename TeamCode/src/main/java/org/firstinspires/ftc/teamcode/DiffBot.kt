package org.firstinspires.ftc.teamcode

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode

class DiffBot(opMode: RechargedLinearOpMode<DiffBot>) : RobotTemplate(opMode, arrayOf("hub1")) {
    val drive = DiffMPDrive(this)
    override fun autoPostInit() {}
}