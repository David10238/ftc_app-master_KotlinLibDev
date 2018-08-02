package com.david.rechargedkotlinlibrary.internal.hardware.management

/**
 * Created by David Lukens on 8/2/2018.
 */

class HardwareThread(val robot:RobotTemplate):Thread(){
    private val components = LinkedHashSet<ThreadedSubsystem>()
    fun addSubsystem(subsystem: ThreadedSubsystem) = components.add(subsystem)
    override fun run() {
        components.forEach({it.start()})
        while (!robot.opMode.isStopRequested)
            components.forEach({it.update()})
    }
}