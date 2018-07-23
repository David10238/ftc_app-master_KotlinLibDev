package org.firstinspires.ftc.teamcode.superClassTemplate;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by David Lukens on 7/23/2018.
 */

public abstract class SuperClass extends LinearOpMode{
	@Override
	public void runOpMode(){
		initiate();
		waitForStart();
		run();
	}

	public abstract void initiate();

	public abstract void run();
}
