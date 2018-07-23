package org.firstinspires.ftc.teamcode.example2.backEnd;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by David Lukens on 7/23/2018.
 */

public abstract class HardwareClass {
	public WeaponsLinearOpMode opMode;
	public HardwareMap         hMap;

	public abstract void init();

	public abstract void autoPostInit();

	void bindOpMode(WeaponsLinearOpMode opMode) {
		this.opMode = opMode;
		hMap = opMode.hardwareMap;
	}
}
