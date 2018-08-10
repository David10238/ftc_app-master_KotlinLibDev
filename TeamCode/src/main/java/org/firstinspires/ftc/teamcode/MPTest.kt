package org.firstinspires.ftc.teamcode

import com.acmerobotics.roadrunner.Pose2d
import com.david.rechargedkotlinlibrary.internal.opMode.FluidAuto

/**
 * Created by David Lukens on 8/9/2018.
 */
class MPTest : FluidAuto<MPBot>({ opMode -> MPBot(opMode) }) {
    override fun run() {
        robot.drive.waitOnTrajectory(robot.drive.trajectoryBuilder()
                .splineTo(Pose2d(30.0, 30.0, 0.0))
                .turn(90.0)
                .build()
        )
    }
}