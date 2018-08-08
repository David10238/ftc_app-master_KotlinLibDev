package com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain

import com.acmerobotics.roadrunner.drive.MecanumDrive
import com.david.rechargedkotlinlibrary.internal.hardware.devices.OptimumDcMotorEx
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.SameThreadSubsystem
import com.qualcomm.robotcore.hardware.DcMotor
import java.util.*
import kotlin.math.abs

/**
 * Created by David Lukens on 8/2/2018.
 */
open class MecDrive(private val lf: OptimumDcMotorEx, private val lb: OptimumDcMotorEx, private val ENCODER_SCALER:Double = 1.0, private val rf: OptimumDcMotorEx, private val rb: OptimumDcMotorEx, val RUN_MODE: DcMotor.RunMode = DcMotor.RunMode.RUN_USING_ENCODER, val RADIUS: Double = 2.0, TRACK_WIDTH: Double): MecanumDrive(TRACK_WIDTH){
    init {
        lf.mode = RUN_MODE
        lb.mode = RUN_MODE
        rf.mode = RUN_MODE
        rb.mode = RUN_MODE
    }

    fun powerTranslation(forward: Double, strafeRight: Double, turnClockwise: Double) = setMotorPowers(forward + strafeRight + turnClockwise, forward - strafeRight + turnClockwise, forward - strafeRight - turnClockwise, forward + strafeRight - turnClockwise)

    override fun setMotorPowers(frontLeft: Double, rearLeft: Double, rearRight: Double, frontRight: Double) {
        val max = Collections.max(listOf(abs(frontLeft), abs(rearLeft), abs(frontRight), abs(rearRight), 1.0))
        lf.power = frontLeft / max
        lb.power = rearLeft / max
        rf.power = frontRight / max
        rb.power = rearRight / max
    }

    fun radiansToInches(radians: Double) = radians * RADIUS * ENCODER_SCALER

    override fun getWheelPositions(): List<Double> {
        val positions = LinkedList<Double>()
        positions.add(radiansToInches(lfRawRadians()))
        positions.add(radiansToInches(lbRawRadians()))
        positions.add(radiansToInches(rfRawRadians()))
        positions.add(radiansToInches(rbRawRadians()))
        return positions
    }

    fun resetEncoders() {
        resetLFEncoder()
        resetLBEncoder()
        resetRFEncoder()
        resetRBEncoder()
    }

    fun resetLFEncoder() = lf.resetEncoder()
    fun resetLBEncoder() = lb.resetEncoder()
    fun resetRFEncoder() = rf.resetEncoder()
    fun resetRBEncoder() = rb.resetEncoder()
    fun lfTicks() = lf.currentPosition
    fun lbTicks() = lb.currentPosition
    fun rfTicks() = rf.currentPosition
    fun rbTicks() = rb.currentPosition
    fun lfRawTicks() = lf.getRawPosition()
    fun lbRawTicks() = lb.getRawPosition()
    fun rfRawTicks() = rf.getRawPosition()
    fun rbRawTicks() = rb.getRawPosition()
    fun lfRadians() = lf.getRadians()
    fun lbRadians() = lb.getRadians()
    fun rfRadians() = rf.getRadians()
    fun rbRadians() = rb.getRadians()
    fun lfRawRadians() = lf.getRawRadians()
    fun lbRawRadians() = lb.getRawRadians()
    fun rfRawRadians() = rf.getRawRadians()
    fun rbRawRadians() = rb.getRawRadians()
}