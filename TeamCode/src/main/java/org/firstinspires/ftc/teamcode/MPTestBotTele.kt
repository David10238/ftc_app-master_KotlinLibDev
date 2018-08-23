package org.firstinspires.ftc.teamcode

import com.david.rechargedkotlinlibrary.internal.opMode.PracticeTeleOp

/**
 * Created by David Lukens on 8/10/2018.
 */
class MPTestBotTele : PracticeTeleOp<MPBot>({ opMode -> MPBot(opMode) }) {
    override fun onLoop() {
        robot.drive.powerTranslation(c1.ly, c1.lx, c1.ry)
        if (gamepad1.a)
            robot.drive.resetPos()
        telemetry.addData("Pos", robot.drive.getPos())
    }
}