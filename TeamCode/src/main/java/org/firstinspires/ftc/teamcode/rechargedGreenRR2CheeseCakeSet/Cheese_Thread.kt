package org.firstinspires.ftc.teamcode.rechargedGreenRR2CheeseCakeSet

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

/**
 * Created by David Lukens on 9/9/2018.
 */

class Cheese_Thread (val opMode:LinearOpMode):Thread(){
    val systems = LinkedHashSet<Cheese_System>()
    fun add(system: Cheese_System) = systems.add(system)
    override fun run(){
        while (!opMode.isStopRequested)
            systems.forEach { it.update() }
    }
}