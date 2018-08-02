package com.david.rechargedkotlinlibrary.internal.opMode

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate

/**
 * Created by David Lukens on 8/2/2018.
 */

class FluidAuto<rt:RobotTemplate> : RechargedLinearOpMode<rt>(){
    override fun runOpMode() {
        alliance = Alliance.FLUID
        handleFlow(true)
    }
}