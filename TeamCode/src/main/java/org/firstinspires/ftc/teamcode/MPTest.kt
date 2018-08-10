package org.firstinspires.ftc.teamcode

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d
import com.acmerobotics.roadrunner.path.heading.ConstantInterpolator
import com.david.rechargedkotlinlibrary.internal.opMode.FluidAuto

/**
 * Created by David Lukens on 8/9/2018.
 */
class MPTest : FluidAuto<MPBot>({ opMode -> MPBot(opMode) }) {
    override fun run() {

        // drive path for side auto from RR
        robot.drive.waitOnTrajectory(trajectory = robot.drive.trajectoryBuilder()
                .forward(60.0)
                .turnTo(-90.0)
                .build())

        robot.drive.waitOnTrajectory(trajectory = robot.drive.trajectoryBuilder()
                .splineTo(Pose2d(30.0, 30.0, 0.0))
                .turn(90.0)
                .build()
        )

        robot.drive.waitOnTrajectory(action = Runnable {
            telemetry.addData("runtime", runtime.seconds())
            telemetry.update()
        }, trajectory = robot.drive.trajectoryBuilder()
                .turn(90.0)
                .forward(10.0)
                .back(10.0)
                .strafeLeft(10.0)
                .strafeRight(10.0)
                .turnTo(10.0)
                .lineTo(Vector2d(0.0, 0.0))
                .lineTo(Vector2d(0.0, 0.0), ConstantInterpolator(45.0))
                .splineTo(Pose2d(Vector2d(0.0, 0.0), 0.0))
                .reverse()
                .reverse()
                .waitFor(2.0)
                .setReversed(false)
                .build())
    }
}