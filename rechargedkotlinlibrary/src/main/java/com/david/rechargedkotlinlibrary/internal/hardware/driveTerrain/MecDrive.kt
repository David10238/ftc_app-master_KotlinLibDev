package com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain

import com.david.rechargedkotlinlibrary.internal.hardware.devices.ThreadedDcMotorEx
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.SameThreadSubsystem
import com.qualcomm.hardware.motors.NeveRest20Gearmotor
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.configuration.MotorType
import java.util.*
import kotlin.math.abs

/**
 * Created by David Lukens on 8/2/2018.
 */
open class MecDrive(robot: RobotTemplate, private val lf:DcMotorEx, private val lb:DcMotorEx, private val rf:DcMotorEx, private val rb:DcMotorEx, val MOTOR_TYPE:MotorType) : SameThreadSubsystem(robot){
    var TICKS_PER_REVOLUTION = MOTOR_TYPE.ticksPerRev

    fun powerTranslation(forward:Double, strafeRight:Double, turnClockwise:Double) = powerMotors(lfp = forward + strafeRight + turnClockwise, lbp = forward - strafeRight + turnClockwise, rfp = forward - strafeRight - turnClockwise, rbp = forward + strafeRight - turnClockwise)

    fun powerMotors(lfp:Double, lbp:Double, rfp:Double, rbp:Double){
        val max = Collections.max(listOf(abs(lfp), abs(lbp), abs(rfp), abs(rbp), 1.0))
        lf.power = lfp / max
        lb.power = lbp / max
        rf.power = rfp / max
        rb.power = rbp / max
    }
}