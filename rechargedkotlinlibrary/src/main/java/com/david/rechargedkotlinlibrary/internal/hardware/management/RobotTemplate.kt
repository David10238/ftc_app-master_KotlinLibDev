package com.david.rechargedkotlinlibrary.internal.hardware.management

import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.hardware.HardwareMap
import java.util.*

/**
 * Created by David Lukens on 8/2/2018.
 */
abstract class RobotTemplate (val opMode:RechargedLinearOpMode<out RobotTemplate>, revHubNames:Array<String>){
    val hMap:HardwareMap = opMode.hardwareMap
    val sameThreadSubsystems = HashSet<SameThreadSubsystem>()
    val thread = HardwareThread(opMode)

    val revHubs = LinkedList<LynxModule>()

    init {
        revHubNames.forEach { revHubs.add(hMap.get(LynxModule::class.java, it)) }
    }
    open fun onStart(){}
    abstract fun autoPostInit()

    fun start(){
        thread.start()
        sameThreadSubsystems.forEach({it.start()})
        onStart()
    }
}