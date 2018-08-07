package com.david.rechargedkotlinlibrary.internal.hardware.devices

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem
import com.qualcomm.robotcore.hardware.*
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit

/**
 * Created by David Lukens on 8/7/2018.
 */
class OptimumDcMotorEx(robot: RobotTemplate, config: String, hub: Int) : DcMotorEx, ThreadedSubsystem(robot) {
    val delegate = robot.hMap.get(DcMotorEx::class.java, config)
    val PORT = delegate.portNumber
    val HUB = robot.getHub(hub)
    private val MOTOR_TYPE = delegate.motorType
    val TICKS_PER_REV = MOTOR_TYPE.ticksPerRev
    var resetPos = 0

    fun ticksToRadians(ticks: Int) = ticks / TICKS_PER_REV * 2 * Math.PI
    fun getRawRadians() = ticksToRadians(getRawPosition())
    fun getRawPosition() = HUB.getEncoder(PORT)
    fun getRadians() = ticksToRadians(currentPosition)
    override fun getCurrentPosition() = getRawPosition() - resetPos
    fun resetEncoder() {
        resetPos = getRawPosition()
    }

    override fun isBusy(): Boolean = HUB.isAtTarget(PORT)
    override fun getPortNumber() = PORT

    var powerCache = 0.0
    var lastPowerCache = 0.0
    override fun update() {
        val pc = powerCache
        if (pc != lastPowerCache)
            delegate.power = pc
        lastPowerCache = pc
    }

    override fun setMotorType(motorType: MotorConfigurationType?) {
        delegate.motorType = motorType
    }

    override fun setPower(power: Double) {
        powerCache = power
    }

    override fun getPower(): Double = powerCache
    override fun getMotorType(): MotorConfigurationType = MOTOR_TYPE
    override fun setMotorDisable() = delegate.setMotorDisable()
    override fun setMotorEnable() = delegate.setMotorEnable()
    override fun isMotorEnabled() = delegate.isMotorEnabled
    override fun getTargetPositionTolerance() = delegate.targetPositionTolerance
    override fun setPowerFloat() = delegate.setPowerFloat()
    override fun getPowerFloat() = delegate.powerFloat
    override fun resetDeviceConfigurationForOpMode() = delegate.resetDeviceConfigurationForOpMode()
    override fun getPIDCoefficients(mode: DcMotor.RunMode?) = delegate.getPIDCoefficients(mode)
    override fun setPIDCoefficients(mode: DcMotor.RunMode?, pidCoefficients: PIDCoefficients?) = delegate.setPIDCoefficients(mode, pidCoefficients)
    override fun getZeroPowerBehavior() = delegate.zeroPowerBehavior
    override fun getDirection(): DcMotorSimple.Direction = delegate.direction
    override fun setDirection(direction: DcMotorSimple.Direction?) {
        delegate.direction = direction
    }

    override fun getTargetPosition() = delegate.targetPosition
    override fun setVelocity(angularRate: Double, unit: AngleUnit?) = delegate.setVelocity(angularRate, unit)
    override fun getController(): DcMotorController = delegate.controller
    override fun close() = delegate.close()
    override fun getVersion() = delegate.version
    override fun getDeviceName() = delegate.deviceName
    override fun setTargetPosition(position: Int) {
        delegate.targetPosition = position
    }

    override fun getMode(): DcMotor.RunMode = delegate.mode
    override fun getConnectionInfo(): String = delegate.connectionInfo
    override fun getVelocity(unit: AngleUnit?) = delegate.getVelocity(unit)
    override fun getManufacturer(): HardwareDevice.Manufacturer = delegate.manufacturer
    override fun setMode(mode: DcMotor.RunMode?) {
        delegate.mode = mode
    }

    override fun setZeroPowerBehavior(zeroPowerBehavior: DcMotor.ZeroPowerBehavior?) {
        delegate.zeroPowerBehavior = zeroPowerBehavior
    }

    override fun setTargetPositionTolerance(tolerance: Int) {
        delegate.targetPositionTolerance = tolerance
    }
}