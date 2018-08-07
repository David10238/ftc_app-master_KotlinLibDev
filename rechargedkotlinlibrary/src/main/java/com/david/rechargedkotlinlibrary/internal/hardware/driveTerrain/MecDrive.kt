package com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain

import com.david.rechargedkotlinlibrary.internal.hardware.devices.OptimumDcMotorEx
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.SameThreadSubsystem
import com.qualcomm.robotcore.hardware.DcMotor
import java.util.*
import kotlin.math.abs

/**
 * Created by David Lukens on 8/2/2018.
 */
open class MecDrive(robot: RobotTemplate, private val lf: OptimumDcMotorEx, private val lb: OptimumDcMotorEx, private val rf: OptimumDcMotorEx, private val rb: OptimumDcMotorEx, val RUN_MODE: DcMotor.RunMode = DcMotor.RunMode.RUN_USING_ENCODER, val RADIUS: Double = 2.0, val BASE_WIDTH: Double) : SameThreadSubsystem(robot) {
    fun powerTranslation(forward: Double, strafeRight: Double, turnClockwise: Double) = powerMotors(lfp = forward + strafeRight + turnClockwise, lbp = forward - strafeRight + turnClockwise, rfp = forward - strafeRight - turnClockwise, rbp = forward + strafeRight - turnClockwise)

    fun powerMotors(lfp: Double, lbp: Double, rfp: Double, rbp: Double) {
        val max = Collections.max(listOf(abs(lfp), abs(lbp), abs(rfp), abs(rbp), 1.0))
        lf.power = lfp / max
        lb.power = lbp / max
        rf.power = rfp / max
        rb.power = rbp / max
    }

    fun radiansToInches(radians: Double) = radians * RADIUS

    fun getWheelPositions(): List<Double> {
        val positions = LinkedList<Double>()
        positions.add(radiansToInches(lf.getRadians()))
        positions.add(radiansToInches(lb.getRadians()))
        positions.add(radiansToInches(rf.getRadians()))
        positions.add(radiansToInches(rb.getRadians()))
        return positions
    }
}