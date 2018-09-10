package org.firstinspires.ftc.teamcode.prototypes

import com.david.rechargedkotlinlibrary.internal.opMode.PracticeTeleOp
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

/**
 * Created by David Lukens on 9/10/2018.
 */
@TeleOp(name = EthanTele.NAME)
class EthanTele : PracticeTeleOp<EthanBot>({ opmode -> EthanBot(opmode) }) {
    companion object {
        const val NAME = "EthanTele"
    }
    override fun onLoop() = robot.drive.openLoopPowerWheels(l = c1.ly, r = c1.ry)
}