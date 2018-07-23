package org.firstinspires.ftc.teamcode.example2.examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.example2.backEnd.HardwareClass;

/**
 * Created by David Lukens on 7/23/2018.
 */

public class MecDrive {
	private final DcMotor lf;
	private final DcMotor lb;
	private final DcMotor rf;
	private final DcMotor rb;

	private final DcMotor.RunMode runMode;
	private final HardwareClass   robot;

	private void setRunMode(DcMotor.RunMode runMode) {
		lf.setMode(runMode);
		lb.setMode(runMode);
		rf.setMode(runMode);
		rb.setMode(runMode);
	}

	public MecDrive(HardwareClass robot, boolean reverseLF, boolean reverseLB, boolean reverseRF, boolean reverseRB, DcMotor.RunMode runMode) {
		lf = robot.hMap.dcMotor.get("lf");
		lb = robot.hMap.dcMotor.get("lb");
		rf = robot.hMap.dcMotor.get("rf");
		rb = robot.hMap.dcMotor.get("rb");

		lf.setDirection(reverseLF ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
		lb.setDirection(reverseRB ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
		rf.setDirection(reverseLF ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
		rb.setDirection(reverseRB ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);

		this.runMode = runMode;
		this.robot = robot;

		setRunMode(runMode);
	}

	public void powerDrive(double forward, double turnClockwise, double strafeRight) {
		double lf = forward + strafeRight + turnClockwise;
		double lb = forward - strafeRight + turnClockwise;
		double rf = forward - strafeRight - turnClockwise;
		double rb = forward + strafeRight - turnClockwise;

		double max = Math.max(Math.abs(lf), Math.abs(lb));
		max = Math.max(max, Math.abs(rf));
		max = Math.max(max, Math.abs(rb));

		if(max > 1.0) {
			lf /= max;
			lb /= max;
			rf /= max;
			rb /= max;
		}

		this.lf.setPower(lf);
		this.lb.setPower(lb);
		this.rf.setPower(rf);
		this.rb.setPower(rb);
	}

	public void resetEncoders() {
		setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		robot.opMode.idle();
		setRunMode(runMode);
	}
}
