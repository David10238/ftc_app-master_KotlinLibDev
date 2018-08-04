package com.david.rechargedkotlinlibrary.internal.hardware.management

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

/**
 * Created by David Lukens on 8/2/2018.
 */

class HardwareThread(val opMode:LinearOpMode):Thread(){
    private val components = LinkedHashSet<ThreadedSubsystem>()
    fun addSubsystem(subsystem: ThreadedSubsystem) = components.add(subsystem)
    override fun run() {
        components.forEach({it.start()})
        while (!opMode.isStopRequested)
            components.forEach({it.update()})
    }
}