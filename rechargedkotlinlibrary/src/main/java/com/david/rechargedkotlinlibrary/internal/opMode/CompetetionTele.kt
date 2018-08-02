package com.david.rechargedkotlinlibrary.internal.opMode

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate

/**
 * Created by David Lukens on 8/2/2018.
 */

abstract class CompetetionTele<rt:RobotTemplate>(createRobot:(RechargedLinearOpMode<rt>) -> rt) : RechargedLinearOpMode<rt>(createRobot){
    var competetion = true
    override fun runOpMode() {
        handleFlow(true)
    }

    override fun run() {
    }

    abstract fun onStart()
    abstract fun onLoop()
}