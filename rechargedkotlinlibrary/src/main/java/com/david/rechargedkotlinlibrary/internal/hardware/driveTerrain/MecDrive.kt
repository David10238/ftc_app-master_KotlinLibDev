package com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain

import com.david.rechargedkotlinlibrary.internal.hardware.devices.CachingDcMotorEx
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.SameThreadSubsystem
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import java.util.*
import kotlin.math.abs

/**
 * Created by David Lukens on 8/2/2018.
 */
open class MecDrive(robot: RobotTemplate, lfConfig:String, lbConfig:String, rfConfig:String, rbConfig:String, runMode:DcMotor.RunMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER, zeroPowerBehavior: DcMotor.ZeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE, lfDirection:DcMotorSimple.Direction = DcMotorSimple.Direction.FORWARD, lbDirection:DcMotorSimple.Direction = DcMotorSimple.Direction.FORWARD, rfDirection:DcMotorSimple.Direction = DcMotorSimple.Direction.FORWARD, rbDirection:DcMotorSimple.Direction = DcMotorSimple.Direction.FORWARD) : SameThreadSubsystem(robot){
    private val lf = CachingDcMotorEx(robot, lfConfig, runMode = runMode, zeroPowerBehavior = zeroPowerBehavior, direction = lfDirection)
    private val lb = CachingDcMotorEx(robot, lbConfig, runMode = runMode, zeroPowerBehavior = zeroPowerBehavior, direction = lbDirection)
    private val rf = CachingDcMotorEx(robot, rfConfig, runMode = runMode, zeroPowerBehavior = zeroPowerBehavior, direction = rfDirection)
    private val rb = CachingDcMotorEx(robot, rbConfig, runMode = runMode, zeroPowerBehavior = zeroPowerBehavior, direction = rbDirection)

    override fun start(){
    }

    fun resetEncoders(){
        lf.resetEncoder()
        lb.resetEncoder()
        rf.resetEncoder()
        rb.resetEncoder()
    }

    fun powerTranslation(forward:Double, strafeRight:Double, turnClockwise:Double) = powerMotors(lfp = forward + strafeRight + turnClockwise, lbp = forward - strafeRight + turnClockwise, rfp = forward - strafeRight - turnClockwise, rbp = forward + strafeRight - turnClockwise)

    fun powerMotors(lfp:Double, lbp:Double, rfp:Double, rbp:Double){
        val max = Collections.max(listOf(abs(lfp), abs(lbp), abs(rfp), abs(rbp), 1.0))
        lf.power = lfp / max
        lb.power = lbp / max
        rf.power = rfp / max
        rb.power = rbp / max
    }
}