
package com.team1389.base;

import com.team1389.base.webserver.WebServer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/*
 * This class is the base of all of the code. Everything else is called from here.
 */

public abstract class Team1389RobotBase extends IterativeRobot {
	
	private WebServer server;
	
	RobotCode robotCode;
	
	private Mode mode;
	
   	public abstract RobotCode getCode();
   	public abstract WebServer getServer();
   	
    public void robotInit() {
    	robotCode = getCode();
    	robotCode.setup();

    	mode = Mode.DISABLED;
    	
    	//start webserver
    	server = getServer();
    	server.start();
    	
    	//configure Strongback
    	Configuration.configureStrongback();

    	LiveWindow.setEnabled(false);
    }
    
    @Override
    public void autonomousInit() {
    	mode = Mode.AUTON;
    	System.out.println("In Team1389RobotBase calling autonstart");
    	robotCode.getAutonomousBase().autonStart();
    }
    private void disabledAuton() {
    	robotCode.getAutonomousBase().autonEnd();
	}


    @Override
    public void teleopInit() {
    	mode = Mode.TELEOP;
    	robotCode.getTeleopBase().start();
    }
    @Override
    public void testInit(){
    	mode = Mode.TEST;
    	robotCode.getTestBase().start();
    }
    public void disabledInit(){
    	switch(mode){
		case AUTON:
			disabledAuton();
			break;
		case DISABLED:
			break;
		case TELEOP:
			disabledTeleop();
			break;
		case TEST:
			disabledTest();
			break;
    	}
    	mode = Mode.DISABLED;
    }

	private void disabledTeleop() {
		robotCode.getTeleopBase().disable();
	}

	private void disabledTest() {
		robotCode.getTestBase().disable();
	}

	@Override
    public void teleopPeriodic() {
    }
	@Override
    public void autonomousPeriodic() {
    }
	@Override
    public void testPeriodic() {
    }
	@Override
	public void disabledPeriodic() {
	}
}
