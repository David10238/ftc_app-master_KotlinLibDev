package org.firstinspires.ftc.teamcode

import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.ConfigData
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.DcMotorMaker
import com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain.MecDrive
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

/**
 * Created by David Lukens on 8/9/2018.
 */
class MPMecDrive(private val robot:RobotTemplate) : MecDrive(
        DcMotorMaker.instantiate(ConfigData(robot, 0, "lf"), direction = DcMotorSimple.Direction.REVERSE),
        DcMotorMaker.instantiate(ConfigData(robot, 0, "lb"), direction = DcMotorSimple.Direction.REVERSE),
        DcMotorMaker.instantiate(ConfigData(robot, 0, "rf")),
        DcMotorMaker.instantiate(ConfigData(robot, 0, "rb")),
        ENCODER_SCALER = 1.5,
        RUN_MODE = DcMotor.RunMode.RUN_USING_ENCODER,
        zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE,
        TRACK_WIDTH = 0.0,
        WHEEL_BASE = 0.0
){

}