package org.firstinspires.ftc.teamcode.example2.backEnd;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.lang.reflect.ParameterizedType;

/**
 * Created by David Lukens on 7/23/2018.
 */

public abstract class WeaponsLinearOpMode<robotType extends HardwareClass> extends LinearOpMode {
	public robotType robot;

	public boolean auto;

	public void handleFlow(boolean auto) {
		this.auto = auto;
		initiate();
		waitForStart();
		run();
	}

	private void initiate() {
		robot = getRobot();

		robot.bindOpMode(this);

		robot.init();
		if(auto)
			robot.autoPostInit();
	}

	abstract public void run();

	robotType getRobot() {
		ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
		Class<robotType>  type       = (Class<robotType>) superClass.getActualTypeArguments()[0];
		try {
			return type.newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
