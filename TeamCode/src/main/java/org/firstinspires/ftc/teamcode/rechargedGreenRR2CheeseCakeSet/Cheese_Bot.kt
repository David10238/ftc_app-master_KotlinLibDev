package org.firstinspires.ftc.teamcode.rechargedGreenRR2CheeseCakeSet

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

/**
 * Created by David Lukens on 9/9/2018.
 */
class Cheese_Bot (opMode:LinearOpMode, auto:Cheese_Autos){
    val thread = Cheese_Thread(opMode)
    val drive = Cheese_Drive(opMode, auto)
    init {
        thread.add(drive)
        thread.add(drive.gyro)
    }
}