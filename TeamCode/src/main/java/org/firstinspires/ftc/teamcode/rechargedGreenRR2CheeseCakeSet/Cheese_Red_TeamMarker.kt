package org.firstinspires.ftc.teamcode.rechargedGreenRR2CheeseCakeSet

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

/**
 * Created by David Lukens on 9/9/2018.
 */
class Cheese_Red_TeamMarker : LinearOpMode(){
    lateinit var robot:Cheese_Bot

    override fun runOpMode() {
        robot = Cheese_Bot(this, Cheese_Autos.Red_TeamMarker)

        waitForStart()

        // score team marker
        robot.drive.drive(1.0, distance = 1000, timout = 4.5)
        sleep(500)
        robot.drive.drive(0.3, distance = -300)
        sleep(500)

        // go in crater
        robot.drive.turn(0.5, 90.0)
        sleep(500)
        robot.drive.turn(0.3, 90.0)
        robot.drive.drive(1.0, distance = 5000, timout = 5.0)
    }
}