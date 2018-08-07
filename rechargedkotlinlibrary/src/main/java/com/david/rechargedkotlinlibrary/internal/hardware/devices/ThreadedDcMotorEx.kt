package com.david.rechargedkotlinlibrary.internal.hardware.devices

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem
import com.qualcomm.robotcore.hardware.*
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit

/**
 * Created by David Lukens on 8/2/2018.
 */
open class ThreadedDcMotorEx(robot: RobotTemplate, config: String, zeroPowerBehavior: DcMotor.ZeroPowerBehavior? = DcMotor.ZeroPowerBehavior.BRAKE, runMode: DcMotor.RunMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER, direction: DcMotorSimple.Direction? = DcMotorSimple.Direction.FORWARD, hub:Int) : ThreadedSubsystem(robot), DcMotorEx {
    private val delegate = hMap.get(DcMotorEx::class.java, config)
    private val TICKS_PER_REV = delegate.motorType.ticksPerRev
    private val controller = delegate.controller
    private val deviceName = delegate.deviceName
    private var resetPos = 0
    private var lastResetPos = 0
    private var rawPosition = 0
    var lastZeroPowerBehaviorCache: DcMotor.ZeroPowerBehavior? = null

    private var targetPosCache = 0
    private var lastTargetPosCache = 0
    private var zeroPowerBehaviorCache = zeroPowerBehavior
    private var connectionInfo = delegate.connectionInfo
    private val version = delegate.version

    private var runModeCache = runMode
    private var lastRunModeChache: DcMotor.RunMode = runMode
    private val portNumber = delegate.portNumber
    private var powerCache = 0.0
    private var lastPowerCache = 0.0
    private val manufacturer = delegate.manufacturer
    private val motorType = delegate.motorType

    private var directionCache = direction
    private var lastDirectionCache = direction

    private val PORT = delegate.portNumber
    private val HUB = robot.getHub(hub)

    init {
        delegate.mode = runMode
        delegate.direction = direction
    }

    override fun update() {
        rawPosition = HUB.getEncoder(PORT)

        val rPos = resetPos
        val tpCache = targetPosCache
        if (tpCache != lastTargetPosCache || rPos != lastResetPos)
            delegate.targetPosition = tpCache
        lastTargetPosCache = tpCache
        lastResetPos = rPos

        val zpbc = zeroPowerBehaviorCache
        if (zpbc != lastZeroPowerBehaviorCache)
            delegate.zeroPowerBehavior = zpbc
        lastZeroPowerBehaviorCache = zpbc

        val pc = powerCache
        if (pc != lastPowerCache)
            delegate.power = pc
        lastPowerCache = pc

        val dc = directionCache
        if (dc != lastDirectionCache)
            delegate.direction = dc
        lastDirectionCache = dc

        val mc = runModeCache
        if(mc != lastRunModeChache)
            delegate.mode = mc
        lastRunModeChache = mc
    }

    override fun setMotorType(motorType: MotorConfigurationType?) {
        delegate.motorType = motorType
    }

    override fun resetDeviceConfigurationForOpMode() = delegate.resetDeviceConfigurationForOpMode()

    override fun getController(): DcMotorController = controller

    override fun getDeviceName(): String = deviceName

    override fun getCurrentPosition(): Int = currentPosition - resetPos

    fun getRawPosition() = rawPosition

    fun getRawRadians() = ticksToRadians(getRawPosition())

    fun getRadians() = ticksToRadians(currentPosition)

    fun ticksToRadians(ticks:Int):Double = 2.0 * Math.PI * ticks / TICKS_PER_REV

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

    override fun isBusy(): Boolean = delegate.isBusy

    override fun getPortNumber(): Int = portNumber
    override fun close() = delegate.close()
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

    override fun getPIDCoefficients(mode: DcMotor.RunMode?): PIDCoefficients = delegate.getPIDCoefficients(mode)

    override fun setPIDCoefficients(mode: DcMotor.RunMode?, pidCoefficients: PIDCoefficients?) {
        delegate.setPIDCoefficients(mode, pidCoefficients)
    }

    override fun setMotorDisable() = delegate.setMotorDisable()

    override fun setVelocity(angularRate: Double, unit: AngleUnit?) = delegate.setVelocity(angularRate, unit)

    override fun isMotorEnabled(): Boolean = delegate.isMotorEnabled

    override fun setMotorEnable() = delegate.setMotorEnable()

    override fun getTargetPositionTolerance(): Int = delegate.targetPositionTolerance

    override fun getVelocity(unit: AngleUnit?): Double = delegate.getVelocity(unit)

    override fun setTargetPositionTolerance(tolerance: Int) {
        delegate.targetPositionTolerance = tolerance
    }
}