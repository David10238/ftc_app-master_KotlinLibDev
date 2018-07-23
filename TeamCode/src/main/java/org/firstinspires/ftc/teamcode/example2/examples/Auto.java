package org.firstinspires.ftc.teamcode.example2.examples;

import org.firstinspires.ftc.teamcode.example2.backEnd.WeaponsAuto;

/**
 * Created by David Lukens on 7/23/2018.
 */

public class Auto extends WeaponsAuto<WeaponBot> {
	@Override
	public void run() {
		robot.drive.powerDrive(1.0, 0.0, 0.0);
		sleep(1000);
		robot.drive.powerDrive(0.0, 1.0, 0.0);
		sleep(1000);
		robot.drive.powerDrive(0.0, 0.0, 1.0);
		sleep(1000);
		robot.drive.powerDrive(0.0, 0.0, 0.0);
	}
}
