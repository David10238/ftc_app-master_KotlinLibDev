package com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain

import com.acmerobotics.roadrunner.drive.TankDrive
import com.david.rechargedkotlinlibrary.internal.hardware.devices.OptimumDcMotorEx
import com.qualcomm.robotcore.hardware.DcMotor
import java.util.*

/**
 * Created by David Lukens on 8/7/2018.
 */
class DifDrive(private val leftMotors:Array<OptimumDcMotorEx>, private val rightMotors:Array<OptimumDcMotorEx>, val RUN_MODE: DcMotor.RunMode = DcMotor.RunMode.RUN_USING_ENCODER, private val ENCODER_SCALER:Double = 1.0, private val RADIUS: Double = 2.0, TRACK_WIDTH: Double) : TankDrive(TRACK_WIDTH){
    init {
        leftMotors.forEach { it.mode = RUN_MODE }
        rightMotors.forEach { it.mode = RUN_MODE }
    }

    override fun getWheelPositions(): List<Double> {
        var lSum = 0.0
        leftMotors.forEach {lSum += it.getRawRadians()}
        var rSum = 0.0
        rightMotors.forEach {rSum += it.getRawRadians()}

        val positions = LinkedList<Double>()
        positions.add(radiansToInches(lSum / 2))
        positions.add(radiansToInches(rSum / 2))
        return positions
    }

    fun radiansToInches(radians: Double) = radians * RADIUS * ENCODER_SCALER

    override fun setMotorPowers(left: Double, right: Double) {
        leftMotors.forEach { it.power = left }
        rightMotors.forEach { it.power = right }
    }
}