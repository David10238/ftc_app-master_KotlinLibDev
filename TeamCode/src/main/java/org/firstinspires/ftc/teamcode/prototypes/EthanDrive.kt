package org.firstinspires.ftc.teamcode.prototypes

import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.david.rechargedkotlinlibrary.internal.hardware.devices.OptimumDcMotorEx
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.ConfigData
import com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain.DiffDrive
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

/**
 * Created by David Lukens on 9/10/2018.
 */


class EthanDrive(robot:RobotTemplate) : DiffDrive(
        CROSSTRACK_PID_COEFFICIENTS = CROSSTRACK_PID_COEFFICIENTS,
        DISPLACEMENT_PID_COEFFICIENTS = DISPLACEMENT_PID_COEFFICIENTS,
        TRACK_WIDTH = TRACK_WIDTH,
        RADIUS = RADIUS,
        ENCODER_SCALER = ENCODER_SCALER,
        kV = kV,
        MAX_ACCEL = MAX_ACCEL,
        MAX_TURN_ACCEL = MAX_TURN_ACCEL,
        MAX_VEL = 1.0 / kV,
        leftMotors = arrayOf(OptimumDcMotorEx(ConfigData(robot, 0, "lf"), mode = runMode),
                             OptimumDcMotorEx(ConfigData(robot, 0, "lb"), mode = runMode)),
        rightMotors = arrayOf(OptimumDcMotorEx(ConfigData(robot, 0, "rf"), mode = runMode, direction = DcMotorSimple.Direction.REVERSE),
                              OptimumDcMotorEx(ConfigData(robot, 0, "rb"), mode = runMode, direction = DcMotorSimple.Direction.REVERSE)),
        robot = robot
){
    companion object Config{
        const val kV = 1.0
        const val MAX_ACCEL = 1.0
        const val MAX_TURN_ACCEL = 1.0
        const val ENCODER_SCALER = 1.0
        const val RADIUS = 2.0
        const val TRACK_WIDTH = 0.0
        val CROSSTRACK_PID_COEFFICIENTS = PIDCoefficients(0.0, 0.0, 0.0)
        val DISPLACEMENT_PID_COEFFICIENTS = PIDCoefficients(0.0, 0.0, 0.0)
        val runMode = DcMotor.RunMode.RUN_USING_ENCODER
    }
}