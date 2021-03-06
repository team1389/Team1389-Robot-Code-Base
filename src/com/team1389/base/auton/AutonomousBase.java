package com.team1389.base.auton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.strongback.Strongback;
import org.strongback.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class AutonomousBase{
	Map<String, AutonMode> modes;

	String selectedAutonName;
	AutonMode runningAutonMode;
	Command runningCommand;
	protected SendableChooser autonModeChooser = new SendableChooser();

	public AutonomousBase() {
		SmartDashboard.putData("Auton Mode Choice", autonModeChooser);
	}
	public void construct(){
		modes = new HashMap<String, AutonMode>();
		List<AutonMode> modesList = provideAutonModes();
		modesList.add(new DoNothingAuton());

		for (AutonMode mode : modesList){
			modes.put(mode.getName(), mode);
		}
		runningCommand = null;
		setSelectedAuton("nothing");
	}
	public String getSelectedAuton(){
		return selectedAutonName;
	}
	public void setSelectedAuton(String selectedAuton){
		this.selectedAutonName = selectedAuton;
		System.out.println("setting, now == =" + this.selectedAutonName);
	}
	public List<AutonMode> getAutonModes(){
		return new ArrayList<AutonMode>(modes.values());
	}
	public void autonStart(){
		setup();
		
		//Checks to see if the user has made a choice of auton mode
		runningAutonMode = (AutonMode) autonModeChooser.getSelected();
		
		//runningAutonMode = modes.get(selectedAutonName);
		
		
		runningCommand = runningAutonMode.getCommand();
		Strongback.restart();
		Strongback.submit(runningCommand);
	}
	public void autonEnd(){
		if (runningAutonMode.shouldCancelCommandsOnAutonEnd()){
			Strongback.killAllCommands();
		}
	}

	protected abstract List<AutonMode> provideAutonModes();
	protected abstract void setup();
}


//package com.team1389.base.auton;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.strongback.Strongback;
//import org.strongback.command.Command;
//
//import com.team1389.base.smartdashboard.AutonModesSmartDashboard;
//
//public abstract class AutonomousBase{
//	Map<String, AutonMode> modes;
//
//	String selectedAutonName;
//	AutonMode runningAutonMode;
//	Command runningCommand;
//	//new
//	AutonModesSmartDashboard smartDashControl;
//
//	public AutonomousBase() {
//	}
//	public void construct(){
//			//new
//		modes = new HashMap<String, AutonMode>();
//
//
//		List<AutonMode> modesList = provideAutonModes();
//		modesList.add(new DoNothingAuton());
//
//		for (AutonMode mode : modesList){
//			modes.put(mode.getName(), mode);
//		}
//
//		runningCommand = null;
//		setSelectedAuton("nothing");
//		
//		System.out.println("got to this line");
//
//		smartDashControl = new AutonModesSmartDashboard(getAutonModes(), selectedAutonName);
//	}
//	public String getSelectedAuton(){
//		return selectedAutonName;
//	}
//	public void setSelectedAuton(String selectedAuton){
//		this.selectedAutonName = selectedAuton;
//		System.out.println("setting, now == =" + this.selectedAutonName);
//	}
//	public List<AutonMode> getAutonModes(){
//		return new ArrayList<AutonMode>(modes.values());
//	}
//	public void autonStart(){
//		setup();
//		
//		//new
//		checkSmartDash();
//		
//		runningAutonMode = modes.get(selectedAutonName);
//		runningCommand = runningAutonMode.getCommand();
//		
//		System.out.println("Running auton " + runningAutonMode.getName());
//		
//		Strongback.restart();
//		Strongback.submit(runningCommand);
//	}
//	public void autonEnd(){
//		if (runningAutonMode.shouldCancelCommandsOnAutonEnd()){
//			Strongback.killAllCommands();
//		}
//	}
//	
//	//new
//	private void checkSmartDash(){
//		String mode = smartDashControl.getSelected();
//		if (mode != null){
//			selectedAutonName = mode;
//		}
//	}
//
//	protected abstract List<AutonMode> provideAutonModes();
//	protected abstract void setup();
//}
