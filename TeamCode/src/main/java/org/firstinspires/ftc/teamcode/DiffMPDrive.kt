package org.firstinspires.ftc.teamcode

import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.ConfigData
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.DcMotorMaker
import com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain.DiffDrive
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.roadRunner.RamseteConstraints
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

/**
 * Created by David Lukens on 8/10/2018.
 */

class DiffMPDrive(robot: RobotTemplate) : DiffDrive(
        robot = robot,
        leftMotors = arrayOf(
                DcMotorMaker.instantiate(ConfigData(robot, 0, "lf"), direction = DcMotorSimple.Direction.REVERSE),
                DcMotorMaker.instantiate(ConfigData(robot, 0, "lb"), direction = DcMotorSimple.Direction.REVERSE)
        ),
        rightMotors = arrayOf(
                DcMotorMaker.instantiate(ConfigData(robot, 0, "rf")),
                DcMotorMaker.instantiate(ConfigData(robot, 0, "rb"))
        ),
        mode = if (robot.opMode.isAutonomous()) DcMotor.RunMode.RUN_USING_ENCODER else DcMotor.RunMode.RUN_WITHOUT_ENCODER,
        zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE,
        DISPLACEMENT_PID_COEFFICIENTS = PIDCoefficients(0.0, 0.0, 0.0),
        CROSSTRACK_PID_COEFFICIENTS = PIDCoefficients(0.0, 0.0, 0.0),
        ramseteConstraints = RamseteConstraints(b = 0.0, zeta = 0.0),
        kV = 1.0,
        kA = 1.0,
        kStatic = 1.0,
        MAX_ACCEL = 0.0,
        MAX_TURN_ACCEL = 0.0,
        TRACK_WIDTH = 0.0
)