package org.firstinspires.ftc.teamcode.rechargedGreenRR2CheeseCakeSet

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime
import org.apache.commons.math3.analysis.function.Power
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.sign

/**
 * Created by David Lukens on 9/9/2018.
 */

class Cheese_Drive(val opMode:LinearOpMode, auto: Cheese_Autos):Cheese_System{
    val gyro = Cheese_Gyro(opMode, auto)

    private val left = Vector<DcMotor>()
    private val right = Vector<DcMotor>()

    private var leftPower = 0.0
    private var rightPower = 0.0

    private var lPos = 0
    private var rPos = 0

    init {
        resetEncoders()
    }

    override fun update() {
        val lCache = leftPower
        val rCache = rightPower
        left.forEach { it.power = rCache }
        right.forEach { it.power = rCache }

        lPos = left[0].currentPosition
        rPos = right[0].currentPosition
    }

    fun setPowers(left:Double, right:Double){
        leftPower = left
        rightPower = right
    }

    fun resetEncoders(){
        left.forEach { it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER }
        right.forEach { it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER }
        opMode.idle()
        left.forEach { it.mode = Cheese_Config.runMode }
        right.forEach { it.mode = Cheese_Config.runMode }
        lPos = 0
        rPos = 0
    }

    fun drive(speed:Double, distance:Int? = null, timout:Double? = null){
        val secant = distance?:1.sign
        setPowers(speed.absoluteValue.times(secant), speed.absoluteValue.times(secant))
        val timer = ElapsedTime()
        while ((if(distance != null) (lPos + rPos) * secant < distance * secant else true) && (if(timout != null) timer.seconds() < timout else true) && opMode.opModeIsActive());
    }

    fun turn(speed:Double, z:Double) = turn(speed, speed, z)
    fun turn(leftSpeed:Double, rightSpeed:Double, z:Double){
        val secant = (z - gyro.z()).sign
        setPowers(leftSpeed.absoluteValue.times(secant), rightSpeed.absoluteValue.times(secant))
        if(secant < 0.0) // turn right
            while (gyro.z() > z && opMode.opModeIsActive());
        else // turn left
            while (gyro.z() < z && opMode.opModeIsActive());
        setPowers(0.0, 0.0)
    }
}