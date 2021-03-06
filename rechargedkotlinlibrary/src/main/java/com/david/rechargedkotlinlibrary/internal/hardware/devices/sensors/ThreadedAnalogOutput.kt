package com.david.rechargedkotlinlibrary.internal.hardware.devices.sensors

import com.david.rechargedkotlinlibrary.internal.hardware.management.RobotTemplate
import com.david.rechargedkotlinlibrary.internal.hardware.management.ThreadedSubsystem

/**
 * Created by David Lukens on 8/3/2018.
 */

class ThreadedAnalogOutput(robot: RobotTemplate, config: String) : ThreadedSubsystem(robot) {
    private val delegate = robot.hMap.analogOutput.get(config)
    private var m: Byte = 0
    private var lm: Byte = 0
    private var f: Int = 0
    private var lf: Int = 0
    private var v: Int = 0
    private var lv: Int = 0

    override fun start() {
    }

    override fun update() {
        val mc: Byte = m
        if (mc != lm)
            delegate.setAnalogOutputMode(mc)
        lm = mc
        val fc: Int = f
        if (fc != lf)
            delegate.setAnalogOutputFrequency(fc)
        lf = fc
        val vc: Int = v
        if (vc != lv)
            delegate.setAnalogOutputVoltage(vc)
        lv = vc
    }

    fun setMode(mode: Byte) {
        m = mode
    }

    fun setFreq(freq: Int) {
        f = freq
    }

    fun setVoltage(voltage: Int) {
        v = voltage
    }
}