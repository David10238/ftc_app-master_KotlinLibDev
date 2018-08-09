package com.david.rechargedkotlinlibrary.external.examples

import com.david.rechargedkotlinlibrary.internal.opMode.FluidAuto

/**
 * Created by David Lukens on 8/9/2018.
 */
class MPTest : FluidAuto<MecBot>({opMode -> MecBot(opMode) }){
    override fun run() {
    }
}