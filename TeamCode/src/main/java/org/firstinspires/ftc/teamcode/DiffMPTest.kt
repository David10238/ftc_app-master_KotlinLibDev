package org.firstinspires.ftc.teamcode

import com.acmerobotics.roadrunner.Pose2d
import com.david.rechargedkotlinlibrary.internal.hardware.driveTerrain.DiffDrive
import com.david.rechargedkotlinlibrary.internal.opMode.FluidAuto

class DiffMPTest : FluidAuto<DiffBot>({ opMode -> DiffBot(opMode) }) {
    override fun run() = robot.drive.waitOnTrajectory(followType = DiffDrive.Follower.PIDVA, trajectory = robot.drive.trajectoryBuilder()
            .splineTo(Pose2d(0.0, 0.0))
            .forward(12.0)
            .turn(0124.112)
            .waitFor(9.0)
            .splineTo(Pose2d(014.4124))
            .back(12.1)
            .waitFor(20.0)
            .turnTo(12.0)
            .build())
}
