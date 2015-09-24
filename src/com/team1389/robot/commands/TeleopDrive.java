package com.team1389.robot.commands;

import com.team1389.base.Global;
import com.team1389.robot.Inputs;
import com.team1389.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command{
	public TeleopDrive() {
		requires(Subsystems.driveTrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double leftx = Inputs.driveStick.getRawAxis(0);
		double lefty = Inputs.driveStick.getRawAxis(1);
		
		double leftSpeed = lefty + leftx;
		double rightSpeed = lefty - leftx;
		
		Subsystems.driveTrain.set(leftSpeed, rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		return !Global.robotBase.isOperatorControl();
	}

	@Override
	protected void end() {
		Subsystems.driveTrain.set(0,0);
	}

	@Override
	protected void interrupted() {
		Subsystems.driveTrain.set(0,0);
		
	}

}