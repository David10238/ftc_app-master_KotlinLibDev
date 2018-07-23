package org.firstinspires.ftc.teamcode.example1.example;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.example1.superClassTemplate.SuperClass;

/**
 * Created by David Lukens on 7/23/2018.
 */

public abstract class ExampleSuperClass extends SuperClass {

	private DcMotor lf;
	private DcMotor lb;
	private DcMotor rf;
	private DcMotor rb;

	@Override
	public void initiate() {
		lf = hardwareMap.dcMotor.get("lf");
		lb = hardwareMap.dcMotor.get("lb");
		rf = hardwareMap.dcMotor.get("rf");
		rb = hardwareMap.dcMotor.get("rb");
	}

	public void powerDrive(double left, double right) {
		double max = Math.max(Math.abs(left), Math.abs(right));
		if(max > 1.0) {
			left /= max;
			right /= max;
		}

		powerLeft(left);
		powerRight(right);
	}

	private void powerLeft(double power) {
		lf.setPower(power);
		lb.setPower(power);
	}

	private void powerRight(double power) {
		rf.setPower(power);
		rb.setPower(power);
	}
}
