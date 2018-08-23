package org.firstinspires.ftc.teamcode

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode

/**
 * Created by David Lukens on 8/9/2018.
 */

class MPBot(opMode: RechargedLinearOpMode<MPBot>) : RobotTemplate(opMode, arrayOf("hub1")) {
    val drive = MPMecDrive(this)
    override fun autoPostInit() {
        return
    }
}