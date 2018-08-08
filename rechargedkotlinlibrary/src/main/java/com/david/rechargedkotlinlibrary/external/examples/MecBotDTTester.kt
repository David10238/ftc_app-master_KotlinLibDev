package com.david.rechargedkotlinlibrary.external.examples

import com.david.rechargedkotlinlibrary.internal.opMode.PracticeTeleOp

/**
 * Created by David Lukens on 8/8/2018.
 */
class MecBotDTTester : PracticeTeleOp<MecBot>({ opMode -> MecBot(opMode) }) {
    var lf = false
    var lb = false
    var rf = false
    var rb = false

    override fun onLoop() {
        val speed = c1.ry
        lf = c1.a
        lb = c1.b
        rf = c1.x
        rb = c1.y
        if (c1.rb) {
            if (lf) robot.drive.resetLFEncoder()
            if (lb) robot.drive.resetLBEncoder()
            if (rf) robot.drive.resetRFEncoder()
            if (rb) robot.drive.resetRBEncoder()
        }
        robot.drive.setMotorPowers(if (lf) speed else 0.0, if (lb) speed else 0.0, if (rf) speed else 0.0, if (rb) speed else 0.0)
    }
}