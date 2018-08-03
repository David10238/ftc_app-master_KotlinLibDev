package com.david.rechargedkotlinlibrary.internal.opMode

import com.qualcomm.robotcore.hardware.Gamepad

class SmartGamepad(c:Gamepad){
    val ly = -c.left_stick_y.toDouble()
    val lx = c.left_stick_x.toDouble()
    val ry = -c.right_stick_y.toDouble()
    val rx = c.right_stick_x.toDouble()
    val lt = c.left_trigger.toDouble()
    val rt = c.right_trigger.toDouble()
    val lb = c.left_bumper
    val rb = c.right_bumper
    val a = c.a
    val b = c.b
    val x = c.x
    val y = c.y
    val letter = a.or(b).or(x).or(y)
    val dUp = c.dpad_up
    val dDown = c.dpad_down
    val dLeft = c.dpad_left
    val dRight = c.dpad_right
    val dpad = dUp.or(dDown).or(dRight).or(dLeft)
    val back = c.back
    val start = c.start
    val atRest = c.atRest()
    val active = atRest.not()
}