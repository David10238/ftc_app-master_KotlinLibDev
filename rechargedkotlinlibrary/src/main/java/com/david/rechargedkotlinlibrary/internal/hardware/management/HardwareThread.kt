package com.david.rechargedkotlinlibrary.internal.hardware.management

/**
 * Created by David Lukens on 8/2/2018.
 */

class HardwareThread(val robot: RobotTemplate) : Thread() {
    private val components = LinkedHashSet<MTSubsystem>()
    fun addSubsystem(subsystem: MTSubsystem) = components.add(subsystem)
    override fun run() {
        components.forEach({ it.start() })
        while (!robot.opMode.isStopRequested) {
            robot.revHubs.forEach({ it.pull() })
            components.forEach({ it.update() })
            robot.revHubs.forEach({ it.push() })
        }
    }
}