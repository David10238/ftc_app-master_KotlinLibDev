package org.firstinspires.ftc.teamcode

import com.acmerobotics.roadrunner.Vector2d
import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.acmerobotics.roadrunner.followers.MecanumPIDVAFollower
import com.acmerobotics.roadrunner.path.LineSegment
import com.acmerobotics.roadrunner.path.Path
import com.acmerobotics.roadrunner.path.QuinticSplineSegment
import com.acmerobotics.roadrunner.trajectory.PathTrajectorySegment
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumConstraints
import com.david.rechargedkotlinlibrary.internal.opMode.FluidAuto
import java.util.*

/**
 * Created by David Lukens on 8/9/2018.
 */
class MPTest : FluidAuto<MPBot>({opMode -> MPBot(opMode) }){
    override fun run() {
        val path = Path(QuinticSplineSegment(
                QuinticSplineSegment.Waypoint(0.0, 0.0, 0.0, 0.0),
                QuinticSplineSegment.Waypoint(40.0, 40.0, 0.0, 0.0)
        ))
        val baseConstraints = DriveConstraints(0.0, 0.0, 0.0, 0.0)
        val constraints = MecanumConstraints(baseConstraints, robot.drive.trackWidth, robot.drive.wheelBase)

        val trajectory = Trajectory(Collections.singletonList(PathTrajectorySegment(path, constraints, 250)))

        val follower = MecanumPIDVAFollower(robot.drive,
                PIDCoefficients(0.0, 0.0, 0.0),
                PIDCoefficients(0.0, 0.0, 0.0),
                0.0,
                0.0,
                0.0)

        waitForStart()

        follower.followTrajectory(trajectory)
        while (opModeIsActive() && follower.isFollowing()) {
            follower.update(robot.drive.poseEstimate)
            robot.drive.updatePoseEstimate()
        }
    }
}