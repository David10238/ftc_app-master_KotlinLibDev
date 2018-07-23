package org.firstinspires.ftc.teamcode.example2.backEnd;

/**
 * Created by David Lukens on 7/23/2018.
 */

public abstract class WeaponsAuto<robotType extends HardwareClass> extends WeaponsLinearOpMode<robotType> {
	@Override
	public void runOpMode() throws InterruptedException {
		handleFlow(true);
	}
}
