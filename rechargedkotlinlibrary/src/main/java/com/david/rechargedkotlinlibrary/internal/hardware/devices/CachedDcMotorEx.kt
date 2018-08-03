package com.david.rechargedkotlinlibrary.internal.hardware.devices

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.qualcomm.robotcore.hardware.*
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit

/**
 * Created by David Lukens on 8/3/2018.
 */

class CachedDcMotorEx(robot: RobotTemplate, config: String, direction:DcMotorSimple.Direction = DcMotorSimple.Direction.FORWARD, zeroPowerBehavior: DcMotor.ZeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE) : DcMotorEx {
    private val delegate = robot.hMap.get(DcMotorEx::class.java, config)
    init {
        this.direction = direction
        this.zeroPowerBehavior = zeroPowerBehavior
    }

    private var powerCache = -1111.0
    override fun getVersion(): Int = delegate.version
    override fun getMode(): DcMotor.RunMode = delegate.mode
    override fun setMotorType(motorType: MotorConfigurationType?) {
        delegate.motorType = motorType
    }

    override fun getPower(): Double = if (Math.abs(power) > 1.0) 0.0 else power
    override fun setPower(power: Double) {
        if(power != powerCache)
            delegate.power = power
        powerCache = power
    }

    override fun resetDeviceConfigurationForOpMode() = delegate.resetDeviceConfigurationForOpMode()
    override fun getPIDCoefficients(mode: DcMotor.RunMode?): PIDCoefficients = delegate.getPIDCoefficients(mode)
    override fun getController(): DcMotorController = delegate.controller
    override fun getDeviceName(): String = delegate.deviceName
    override fun getPortNumber(): Int = delegate.portNumber
    override fun isBusy(): Boolean = delegate.isBusy
    override fun close() = delegate.close()
    override fun getManufacturer(): HardwareDevice.Manufacturer = delegate.manufacturer
    override fun setPowerFloat() = delegate.setPowerFloat()
    override fun getCurrentPosition(): Int = delegate.currentPosition
    override fun setTargetPosition(position: Int) {
        delegate.targetPosition = position
    }

    override fun getPowerFloat(): Boolean = delegate.powerFloat
    override fun setPIDCoefficients(mode: DcMotor.RunMode?, pidCoefficients: PIDCoefficients?) = delegate.setPIDCoefficients(mode, pidCoefficients)
    override fun setMotorDisable() = delegate.setMotorDisable()
    override fun setVelocity(angularRate: Double, unit: AngleUnit?) = delegate.setVelocity(angularRate, unit)
    override fun isMotorEnabled(): Boolean = delegate.isMotorEnabled
    override fun getConnectionInfo(): String = delegate.connectionInfo
    override fun setMotorEnable() = delegate.setMotorEnable()
    override fun setMode(mode: DcMotor.RunMode?) {
        delegate.mode = mode
    }

    override fun setDirection(direction: DcMotorSimple.Direction?) {
        delegate.direction = direction
    }

    override fun getMotorType(): MotorConfigurationType = delegate.motorType
    override fun getTargetPositionTolerance(): Int = delegate.targetPositionTolerance
    override fun getTargetPosition(): Int = delegate.targetPosition
    override fun getVelocity(unit: AngleUnit?): Double = delegate.getVelocity(unit)
    override fun getDirection(): DcMotorSimple.Direction = delegate.direction
    override fun setZeroPowerBehavior(zeroPowerBehavior: DcMotor.ZeroPowerBehavior?) {
        delegate.zeroPowerBehavior = zeroPowerBehavior
    }

    override fun setTargetPositionTolerance(tolerance: Int) {
        delegate.targetPositionTolerance = tolerance
    }

    override fun getZeroPowerBehavior(): DcMotor.ZeroPowerBehavior = delegate.zeroPowerBehavior
}