package com.david.rechargedkotlinlibrary.external.examples

import com.david.rechargedkotlinlibrary.internal.opMode.CompetetionTele
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

/**
 * Created by David Lukens on 8/2/2018.
 */
@TeleOp(name = MecTele.NAME)
class MecTele : CompetetionTele<MecBot>({opMode -> MecBot(opMode)}) {
    companion object {
        const val NAME = "MecTele"
    }
    override fun onStart() {
    }
    override fun onLoop() {
        robot.drive.powerTranslation((-gamepad1.left_stick_y - gamepad1.right_stick_y).toDouble() / 2, (gamepad1.right_trigger - gamepad1.left_trigger).toDouble(), (-gamepad1.left_stick_y + gamepad1.right_stick_y).toDouble() / 2)
    }
}