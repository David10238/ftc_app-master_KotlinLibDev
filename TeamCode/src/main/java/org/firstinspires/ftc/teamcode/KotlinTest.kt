package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

/**
 * Created by David Lukens on 8/1/2018.
 */

@TeleOp(name = "KotlinTest")
class KotlinTest : LinearOpMode(){
    lateinit var lf:DcMotor
    lateinit var lb:DcMotor
    lateinit var rf:DcMotor
    lateinit var rb:DcMotor

    override fun runOpMode() {
        lf = hardwareMap.dcMotor.get("lf")
        lb = hardwareMap.dcMotor.get("lb")
        rf = hardwareMap.dcMotor.get("rf")
        rb = hardwareMap.dcMotor.get("rb")

        rf.direction = DcMotorSimple.Direction.REVERSE
        rb.direction = DcMotorSimple.Direction.REVERSE

        lf.mode = DcMotor.RunMode.RUN_USING_ENCODER
        lb.mode = DcMotor.RunMode.RUN_USING_ENCODER
        rf.mode = DcMotor.RunMode.RUN_USING_ENCODER
        rb.mode = DcMotor.RunMode.RUN_USING_ENCODER

        while(opModeIsActive()){
            power(-gamepad1.left_stick_y.toDouble(), -gamepad1.right_stick_y.toDouble())
        }
    }

    fun power(l:Double, r:Double){
        lf.power = l
        lb.power = l
        rf.power = r
        rb.power = r
    }
}