package org.firstinspires.ftc.teamcode

import com.acmerobotics.roadrunner.drive.MecanumDrive
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.ConfigData
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.DcMotorMaker
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.util.MathUtil
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

/**
 * Created by David Lukens on 8/9/2018.
 */
class MPMecDrive(private val robot:RobotTemplate) : MecanumDrive(1.0, 1.0){
    private val mode = if(robot.opMode.isAutonomous()) DcMotor.RunMode.RUN_USING_ENCODER else DcMotor.RunMode.RUN_WITHOUT_ENCODER
    private val lf = DcMotorMaker.instantiate(ConfigData(robot, 0, "lf"), runMode = mode, direction = DcMotorSimple.Direction.REVERSE)
    private val lb = DcMotorMaker.instantiate(ConfigData(robot, 0, "lb"), runMode = mode, direction = DcMotorSimple.Direction.REVERSE)
    private val rf = DcMotorMaker.instantiate(ConfigData(robot, 0, "rf"), runMode = mode)
    private val rb = DcMotorMaker.instantiate(ConfigData(robot, 0, "rb"), runMode = mode)

    override fun getWheelPositions(): List<Double> {
        return listOf(toInches(lf.getRawRadians()), toInches(lb.getRawRadians()), toInches(rf.getRawRadians()), toInches(rb.getRawRadians()))
    }

    override fun setMotorPowers(frontLeft: Double, rearLeft: Double, rearRight: Double, frontRight: Double) {
        lf.power = frontLeft
        lb.power = rearLeft
        rf.power = frontRight
        rb.power = rearRight
    }

    fun toInches(radians:Double) = MathUtil.radiansToInches(radians * 1.5, 4.0)
}