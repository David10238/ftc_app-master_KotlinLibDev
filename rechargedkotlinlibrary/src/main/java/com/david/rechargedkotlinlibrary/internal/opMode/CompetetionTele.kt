package com.david.rechargedkotlinlibrary.internal.opMode

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.jdroids.robotlib.gamepad.EnhancedGamepad

/**
 * Created by David Lukens on 8/2/2018.
 */

abstract class CompetetionTele<rt:RobotTemplate>(createRobot:(RechargedLinearOpMode<rt>) -> rt) : RechargedLinearOpMode<rt>(createRobot){
    var practice = true
    lateinit var c1:SimpleController
    lateinit var c2:SimpleController
    lateinit var ec1:EnhancedGamepad
    lateinit var ec2:EnhancedGamepad
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        handleFlow(true)
    }

    @Throws(InterruptedException::class)
    override fun run() {
        ec1 = EnhancedGamepad(gamepad1)
        ec2 = EnhancedGamepad(gamepad2)
        onStart()
        loopWhile({practice || runtime.seconds() < 120.0}, {
            c1 = SimpleController(gamepad1)
            c2 = SimpleController(gamepad2)
            onLoop()
            telemetry.update()
        })
    }

    @Throws(InterruptedException::class)
    open fun onStart(){}
    @Throws(InterruptedException::class)
    abstract fun onLoop()
}