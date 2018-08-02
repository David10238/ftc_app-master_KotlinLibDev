package com.david.rechargedkotlinlibrary.internal.opMode

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime

/**
 * Created by David Lukens on 8/2/2018.
 */
abstract class RechargedLinearOpMode<rt:RobotTemplate>(private val createRobot:(RechargedLinearOpMode<rt>) -> rt) : LinearOpMode(){
    lateinit var robot:rt

    var alliance:Alliance = Alliance.FLUID
    private var autonomous:Boolean = false
    fun isAutonomous():Boolean = autonomous

    val runtime:ElapsedTime = ElapsedTime()

    fun handleFlow(autonomous:Boolean){
        this.autonomous = autonomous
        robot = createRobot(this)
        if(autonomous)
            robot.start()
        waitForStart()
        if(autonomous)
            robot.autoPostInit()
        if(!autonomous)
            robot.start()
        runtime.reset()
    }
}