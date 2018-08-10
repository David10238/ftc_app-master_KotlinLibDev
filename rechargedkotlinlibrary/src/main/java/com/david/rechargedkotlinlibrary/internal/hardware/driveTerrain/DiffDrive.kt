package com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d
import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.acmerobotics.roadrunner.drive.TankDrive
import com.acmerobotics.roadrunner.followers.*
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints
import com.acmerobotics.roadrunner.trajectory.constraints.TankConstraints
import com.david.rechargedkotlinlibrary.internal.hardware.devices.OptimumDcMotorEx
import com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors.odometry.Localizer
import com.david.rechargedkotlinlibrary.internal.hardware.management.MTSubsystem
import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.qualcomm.robotcore.hardware.DcMotor
import java.util.*

/**
 * Created by David Lukens on 8/7/2018.
 */
abstract class DiffDrive(
        private val robot: RobotTemplate,
        private val leftMotors: Array<OptimumDcMotorEx>,
        private val rightMotors: Array<OptimumDcMotorEx>,
        mode: DcMotor.RunMode = DcMotor.RunMode.RUN_USING_ENCODER,
        zeroPowerBehavior: DcMotor.ZeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE,
        private val ENCODER_SCALER: Double = 1.0,
        private val RADIUS: Double = 2.0,
        DISPLACEMENT_PID_COEFFICIENTS: PIDCoefficients,
        CROSSTRACK_PID_COEFFICIENTS: PIDCoefficients,
        b:Double,
        zeta:Double,
        kA: Double,
        kV: Double,
        kStatic: Double,
        MAX_VEL: Double = 1.0 / kV,
        MAX_ACCEL: Double,
        MAX_TURN_ACCEL: Double,
        var followerType:Follower = Follower.PIDVA,
        TRACK_WIDTH: Double,
        localizer: Localizer? = null)
    : TankDrive(TRACK_WIDTH), MTSubsystem, Localizer {
    private val localizer = localizer?:this
    private val HARD_MAX_VEL: Double = 1.0 / kV
    override var biasPose = Pose2d(Vector2d(0.0, 0.0), 0.0)

    init {
        leftMotors.forEach {
            it.mode = mode
            it.zeroPowerBehavior = zeroPowerBehavior
        }
        rightMotors.forEach {
            it.mode = mode
            it.zeroPowerBehavior = zeroPowerBehavior
        }
        robot.thread.addSubsystem(this)
    }

    private val baseConstraints = DriveConstraints(1.0 / kV, MAX_VEL, MAX_ACCEL, MAX_TURN_ACCEL)
    val hardConstraints = TankConstraints(baseConstraints, trackWidth)

    fun waitOnFollower(condition: () -> Boolean = { true }, action: Runnable? = null, follower:TrajectoryFollower) {
        while (robot.opMode.opModeIsActive() && follower.isFollowing() && condition()) {
            follower.update(localizer.getPos())
            action?.run()
        }
    }

    fun waitOnTrajectory(condition: () -> Boolean = { true }, action: Runnable? = null, trajectory: Trajectory, followType: Follower = followerType) {
        val follower = getFollower(followType)
        follower.followTrajectory(trajectory)
        waitOnFollower(condition, action, follower)
    }

    fun trajectoryBuilder(pos: Pose2d = localizer.getPos(), constraints: TankConstraints = hardConstraints) = TrajectoryBuilder(pos, constraints)

    private val followerPIDVA = TankPIDVAFollower(drive = this, displacementCoeffs = DISPLACEMENT_PID_COEFFICIENTS, crossTrackCoeffs =  CROSSTRACK_PID_COEFFICIENTS, kV = kV, kA = kA, kStatic = kStatic)
    private val followerRamsete = RamseteFollower(drive = this, b = b, zeta = zeta, kV = kV, kA = kA, kStatic = kStatic)

    enum class Follower{
        RAMSETE,
        PIDVA,
    }

    fun getFollower(type:Follower = Follower.PIDVA):TrajectoryFollower{
        return when(type){
            Follower.PIDVA -> followerPIDVA
            Follower.RAMSETE -> followerRamsete
        }
    }

    override fun getWheelPositions(): List<Double> {
        var lSum = 0.0
        leftMotors.forEach { lSum += it.getRawRadians() }
        var rSum = 0.0
        rightMotors.forEach { rSum += it.getRawRadians() }

        val positions = LinkedList<Double>()
        positions.add(radiansToInches(lSum / leftMotors.size))
        positions.add(radiansToInches(rSum / rightMotors.size))
        return positions
    }

    fun radiansToInches(radians: Double) = radians * RADIUS * ENCODER_SCALER

    override fun setMotorPowers(left: Double, right: Double) {
        leftMotors.forEach { it.power = left }
        rightMotors.forEach { it.power = right }
    }

    override fun getRawPos() = poseEstimate
    override fun updatePos() = updatePoseEstimate()

    override fun update() = localizer.updatePos()

    override fun start() {
    }
}