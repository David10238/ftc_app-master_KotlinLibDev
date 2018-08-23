package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import java.util.*
import kotlin.math.abs

/**
 * Created by David Lukens on 8/1/2018.
 */

@TeleOp(name = "KotlinTest")
class KotlinTest : LinearOpMode() {
    private lateinit var lf: DcMotor
    private lateinit var lb: DcMotor
    private lateinit var rf: DcMotor
    private lateinit var rb: DcMotor

    override fun runOpMode() {
        lf = hardwareMap.dcMotor.get("lf")
        lb = hardwareMap.dcMotor.get("lb")
        rf = hardwareMap.dcMotor.get("rf")
        rb = hardwareMap.dcMotor.get("rb")

        lf.direction = DcMotorSimple.Direction.REVERSE
        lb.direction = DcMotorSimple.Direction.REVERSE

        lf.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        lb.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rf.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rb.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        /*lf.mode = DcMotor.RunMode.RUN_USING_ENCODER
        lb.mode = DcMotor.RunMode.RUN_USING_ENCODER
        rf.mode = DcMotor.RunMode.RUN_USING_ENCODER
        rb.mode = DcMotor.RunMode.RUN_USING_ENCODER*/

        waitForStart()

        while (opModeIsActive()) {
            power(-gamepad1.left_stick_y.toDouble(), gamepad1.left_stick_x.toDouble(), gamepad1.right_stick_x.toDouble())
        }
    }

    fun power(f: Double, sr: Double, tc: Double) {
        var lfp: Double = f + sr + tc
        var lbp: Double = f - sr + tc
        var rfp: Double = f - sr - tc
        var rbp: Double = f + sr - tc
        val max = Collections.max(listOf(abs(lfp), abs(lbp), abs(rfp), abs(rbp), 1.0))
        lfp /= max
        lbp /= max
        rfp /= max
        rbp /= max
        lf.power = lfp
        lb.power = lbp
        rf.power = rfp
        rb.power = rbp
    }
}