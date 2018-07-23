package org.firstinspires.ftc.teamcode.example2.examples;

import org.firstinspires.ftc.teamcode.example2.backEnd.WeaponsTeleOp;

/**
 * Created by David Lukens on 7/23/2018.
 */

public class TeleOp extends WeaponsTeleOp<WeaponBot> {
	public static final String NAME = "MecTele";

	@Override
	public void run() {
		while(opModeIsActive()) {
			double ly = -gamepad1.left_stick_y;
			double ry = -gamepad1.right_stick_y;
			robot.drive.powerDrive((ly + ry) / 2, (ly - ry) / 2, gamepad1.right_trigger - gamepad1.left_trigger);
		}
	}
}
