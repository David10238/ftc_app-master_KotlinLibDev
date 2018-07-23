package org.firstinspires.ftc.teamcode.example;

/**
 * Created by David Lukens on 7/23/2018.
 */

public class TeleOp extends ExampleSuperClass{
	@Override
	public void run() {
		while(opModeIsActive()){
			powerDrive(-gamepad1.left_stick_y * (1.0 - gamepad1.left_trigger), -gamepad1.right_stick_y * (1.0 - gamepad1.right_trigger));
		}
	}
}
