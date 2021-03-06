package org.firstinspires.ftc.teamcode.prototypes

import com.acmerobotics.roadrunner.drive.Drive
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.david.rechargedkotlinlibrary.internal.util.AutoTransitionerKotlin
import com.qualcomm.hardware.bosch.BNO055IMU

/**
 * Created by David Lukens on 9/10/2018.
 */
class EthanBot(opmode:RechargedLinearOpMode<EthanBot>) : RobotTemplate(opmode, arrayOf("hub1")){
    val drive = EthanDrive(robot = this)
    override fun autoPostInit() = AutoTransitionerKotlin.transitionOnStop(opMode, EthanTele.NAME)
    override fun getDrive():Drive = drive
    override fun getGyro():BNO055IMU = hMap.get(BNO055IMU::class.java, "imu")
}