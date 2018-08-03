package com.david.rechargedkotlinlibrary.internal.opMode

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate

/**
 * Created by David Lukens on 8/2/2018.
 */

abstract class CompetetionTele<rt:RobotTemplate>(createRobot:(RechargedLinearOpMode<rt>) -> rt) : RechargedLinearOpMode<rt>(createRobot){
    var practice = true
    lateinit var c1:SmartGamepad
    lateinit var c2:SmartGamepad
    override fun runOpMode() {
        handleFlow(true)
    }

    override fun run() {
        onStart()
        loopWhile({practice || runtime.seconds() < 120.0}, {
            c1 = SmartGamepad(gamepad1)
            c2 = SmartGamepad(gamepad2)
            onLoop()
        })
    }

    open fun onStart(){}
    abstract fun onLoop()
}