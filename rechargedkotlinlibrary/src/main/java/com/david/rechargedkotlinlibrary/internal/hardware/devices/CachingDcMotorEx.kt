package com.david.rechargedkotlinlibrary.internal.hardware.devices

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem
import com.qualcomm.robotcore.hardware.*
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit

/**
 * Created by David Lukens on 8/2/2018.
 */
open class CachingDcMotorEx(robot: RobotTemplate, config: String, zeroPowerBehavior: DcMotor.ZeroPowerBehavior? = DcMotor.ZeroPowerBehavior.BRAKE, runMode: DcMotor.RunMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER, direction: DcMotorSimple.Direction? = DcMotorSimple.Direction.FORWARD) : ThreadedSubsystem(robot), DcMotorEx {
    private val delagate = robot.hMap.get(DcMotorEx::class.java, config)
    private val ticksPerRev = delagate.motorType.ticksPerRev
    private val controller = delagate.controller
    private val deviceName = delagate.deviceName
    private var resetPos = 0
    private var lastResetPos = 0
    private var rawPosition = 0
    var lastZeroPowerBehaviorCache: DcMotor.ZeroPowerBehavior? = null

    private var targetPosCache = 0
    private var lastTargetPosCache = 0
    private var zeroPowerBehaviorCache = zeroPowerBehavior
    private var connectionInfo = delagate.connectionInfo
    private val version = delagate.version

    private var runModeCache = runMode
    private var lastRunModeChache: DcMotor.RunMode = runMode
    private val portNumber = delagate.portNumber
    private var powerCache = 0.0
    private var lastPowerCache = 0.0
    private val manufacturer = delagate.manufacturer
    private val motorType = delagate.motorType

    private var directionCache = direction
    private var lastDirectionCache = direction

    init {
        delagate.mode = runMode
        delagate.direction = direction
    }

    override fun start() {
    }

    override fun update() {
        rawPosition = delagate.currentPosition

        val rPos = resetPos
        val tpCache = targetPosCache
        if (tpCache != lastTargetPosCache || rPos != lastResetPos)
            delagate.targetPosition = tpCache
        lastTargetPosCache = tpCache
        lastResetPos = rPos

        val zpbc = zeroPowerBehaviorCache
        if (zpbc != lastZeroPowerBehaviorCache)
            delagate.zeroPowerBehavior = zpbc
        lastZeroPowerBehaviorCache = zpbc

        val pc = powerCache
        if (pc != lastPowerCache)
            delagate.power = pc
        lastPowerCache = pc

        val dc = directionCache
        if (dc != lastDirectionCache)
            delagate.direction = dc
        lastDirectionCache = dc
    }

    override fun setMotorType(motorType: MotorConfigurationType?) {
        delagate.motorType = motorType
    }

    override fun resetDeviceConfigurationForOpMode() = delagate.resetDeviceConfigurationForOpMode()

    override fun getController(): DcMotorController = controller

    override fun getDeviceName(): String = deviceName

    override fun getCurrentPosition(): Int = currentPosition - resetPos

    fun getRawPosition(): Int = rawPosition

    fun resetEncoder() {
        resetPos = getRawPosition()
    }

    override fun setTargetPosition(position: Int) {
        targetPosCache = position
    }

    override fun getPowerFloat(): Boolean = zeroPowerBehavior == DcMotor.ZeroPowerBehavior.FLOAT

    override fun getZeroPowerBehavior(): DcMotor.ZeroPowerBehavior {
        when (zeroPowerBehaviorCache) {
            DcMotor.ZeroPowerBehavior.BRAKE -> return DcMotor.ZeroPowerBehavior.BRAKE
            DcMotor.ZeroPowerBehavior.FLOAT -> return DcMotor.ZeroPowerBehavior.FLOAT
            else -> return DcMotor.ZeroPowerBehavior.BRAKE
        }
    }

    override fun setZeroPowerBehavior(zeroPowerBehavior: DcMotor.ZeroPowerBehavior?) {
        zeroPowerBehaviorCache = zeroPowerBehavior
    }

    override fun getConnectionInfo(): String = connectionInfo

    override fun getVersion(): Int = version

    override fun getMode(): DcMotor.RunMode = runModeCache

    override fun setMode(mode: DcMotor.RunMode?) {
        when (mode) {
            DcMotor.RunMode.RUN_WITHOUT_ENCODER -> runModeCache = DcMotor.RunMode.RUN_WITHOUT_ENCODER
            DcMotor.RunMode.RUN_USING_ENCODER -> runModeCache = DcMotor.RunMode.RUN_USING_ENCODER
            DcMotor.RunMode.STOP_AND_RESET_ENCODER -> runModeCache = DcMotor.RunMode.STOP_AND_RESET_ENCODER
            DcMotor.RunMode.RUN_TO_POSITION -> runModeCache = DcMotor.RunMode.RUN_USING_ENCODER
        }
    }

    override fun isBusy(): Boolean = delagate.isBusy

    override fun getPortNumber(): Int = portNumber
    override fun close() = delagate.close()
    override fun setPowerFloat() {
        zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
    }

    override fun getPower(): Double = powerCache

    override fun setPower(power: Double) {
        powerCache = power
    }

    override fun getManufacturer(): HardwareDevice.Manufacturer = manufacturer

    override fun getMotorType(): MotorConfigurationType = motorType

    override fun getTargetPosition(): Int = targetPosCache

    override fun setDirection(direction: DcMotorSimple.Direction?) {
        directionCache = direction
    }

    override fun getDirection(): DcMotorSimple.Direction {
        when (directionCache) {
            DcMotorSimple.Direction.FORWARD -> return DcMotorSimple.Direction.FORWARD
            DcMotorSimple.Direction.REVERSE -> return DcMotorSimple.Direction.REVERSE
        }
        return DcMotorSimple.Direction.FORWARD
    }

    override fun getPIDCoefficients(mode: DcMotor.RunMode?): PIDCoefficients = delagate.getPIDCoefficients(mode)

    override fun setPIDCoefficients(mode: DcMotor.RunMode?, pidCoefficients: PIDCoefficients?) {
        delagate.setPIDCoefficients(mode, pidCoefficients)
    }

    override fun setMotorDisable() = delagate.setMotorDisable()

    override fun setVelocity(angularRate: Double, unit: AngleUnit?) = delagate.setVelocity(angularRate, unit)

    override fun isMotorEnabled(): Boolean = delagate.isMotorEnabled

    override fun setMotorEnable() = delagate.setMotorEnable()

    override fun getTargetPositionTolerance(): Int = delagate.targetPositionTolerance

    override fun getVelocity(unit: AngleUnit?): Double = delagate.getVelocity(unit)

    override fun setTargetPositionTolerance(tolerance: Int) {
        delagate.targetPositionTolerance = tolerance
    }
}