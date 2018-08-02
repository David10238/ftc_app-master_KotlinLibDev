package com.david.rechargedkotlinlibrary.internal.hardware.management

import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.qualcomm.robotcore.hardware.HardwareMap

/**
 * Created by David Lukens on 8/2/2018.
 */
abstract class RobotTemplate (val opMode:RechargedLinearOpMode<RobotTemplate>){
    val sameThreadSubsystems = HashSet<SameThreadSubsystem>()
    val thread = HardwareThread(this)

    val hMap:HardwareMap
    init {
        init()
        hMap = opMode.hardwareMap
    }

    abstract fun init()
    abstract fun onStart()
    abstract fun autoPostInit()

    fun start(){
        thread.start()
        sameThreadSubsystems.forEach({it.start()})
        onStart()
    }
}