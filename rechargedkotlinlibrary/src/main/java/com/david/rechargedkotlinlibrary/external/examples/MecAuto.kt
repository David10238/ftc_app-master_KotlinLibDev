package com.david.rechargedkotlinlibrary.external.examples

import com.david.rechargedkotlinlibrary.internal.opMode.RedAuto

/**
 * Created by David Lukens on 8/3/2018.
 */
class MecAuto : RedAuto<MecBot>({opMode -> MecBot(opMode) }){
    @Throws(InterruptedException::class)
    override fun run(){
        robot.drive.powerTranslation(0.5, 0.0, 0.0)
        sleep(2500)
        robot.drive.powerTranslation(0.0, 0.0, 0.0)
        sleep(1000)
        robot.drive.powerTranslation(0.5, 0.0, 0.0)
    }
}