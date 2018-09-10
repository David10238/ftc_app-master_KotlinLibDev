package org.firstinspires.ftc.teamcode.rechargedGreenRR2CheeseCakeSet

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference

/**
 * Created by David Lukens on 9/9/2018.
 */

class Cheese_Gyro(val opMode:OpMode, auto: Cheese_Autos) : Cheese_System{
    private val imu = opMode.hardwareMap.get(BNO055IMU::class.java, Cheese_Config.imuName)
    private val bias = auto.start_heading

    init {
        val params = BNO055IMU.Parameters()
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES
        params.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC
        params.calibrationDataFile = "BNO055IMUCalibration.json"
        params.loggingEnabled = true
        params.loggingTag = "IMU"
        params.accelerationIntegrationAlgorithm = JustLoggingAccelerationIntegrator()
    }

    private var z = 0.0
    private var lastRead = 0.0
    override fun update() {
        val read = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle.toDouble()
        var dz = read - lastRead
        if(dz < -180)
            dz += 360
        else if(dz > 180)
            dz -= 360
        z += dz
        lastRead = read

    }

    fun z() = z + bias
}