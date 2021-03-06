package org.firstinspires.ftc.teamcode.prototypes

import com.david.rechargedkotlinlibrary.internal.roadRunner.FeedforwardTuningOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class EthanFFTuner:FeedforwardTuningOpMode<EthanBot>({opMode -> EthanBot(opMode) }, distance = 100.0, wheelDiameter = 4.0, wheelMotorRpm = 340.0)