package com.david.rechargedkotlinlibrary.internal.hardware.management

/**
 * Created by David Lukens on 8/2/2018.
 */
abstract class Subsystem (robot:RobotTemplate){
    protected val hMap = robot.hMap
    open fun start(){}
}