package org.firstinspires.ftc.teamcode.example2.examples;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.example2.backEnd.AutoTransitioner;
import org.firstinspires.ftc.teamcode.example2.backEnd.HardwareClass;

/**
 * Created by David Lukens on 7/23/2018.
 */

public class WeaponBot extends HardwareClass {
	MecDrive drive;

	@Override
	public void init() {
		drive = new MecDrive(this, false, false, true, true, opMode.auto ? DcMotor.RunMode.RUN_USING_ENCODER : DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	}

	@Override
	public void autoPostInit() {
		AutoTransitioner.transitionOnStop(opMode, TeleOp.NAME);
		drive.resetEncoders();
	}
}
