package com.david.rechargedkotlinlibrary.internal.hardware.management

import com.david.rechargedkotlinlibrary.internal.opMode.RechargedLinearOpMode
import com.qualcomm.robotcore.hardware.HardwareMap

/**
 * Created by David Lukens on 8/2/2018.
 */
abstract class RobotTemplate (val opMode:RechargedLinearOpMode<out RobotTemplate>){
    val sameThreadSubsystems = HashSet<SameThreadSubsystem>()
    val thread = HardwareThread(opMode)
    val hMap:HardwareMap = opMode.hardwareMap
    init {
        init()
    }
    open fun init(){}
    open fun onStart(){}
    abstract fun autoPostInit()

    fun start(){
        thread.start()
        sameThreadSubsystems.forEach({it.start()})
        onStart()
    }
}