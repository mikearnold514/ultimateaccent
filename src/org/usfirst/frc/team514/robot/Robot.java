/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team514.robot;


import org.usfirst.frc.team514.robot.subsystems.DriveUtil;
import org.usfirst.frc.team514.robot.subsystems.LiftUtil;
import org.usfirst.frc.team514.robot.subsystems.ShotAngleUtil;
import org.usfirst.frc.team514.robot.subsystems.ShotMotorUtil;
import org.usfirst.frc.team514.robot.subsystems.ShotUtil;
import org.usfirst.frc.team514.robot.subsystems.TargetsUtil;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    //Command closeGate;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	public static OI oi;
	public static final DriveUtil driveUtil = new DriveUtil();
	public static final LiftUtil liftUtil = new LiftUtil();
	public static final ShotAngleUtil angleUtil = new ShotAngleUtil();
	public static final ShotMotorUtil motorUtil = new ShotMotorUtil();
	public static final ShotUtil shotUtil = new ShotUtil();
	public static final TargetsUtil targetsUtil = new TargetsUtil();
	
	
    public void robotInit() {
        // instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();
        //closeGate = new MAG_CloseGate();
        
        // Initialize all subsystems
        //CommandBase.init();
    	oi = new OI();
    }
    
    public void disabledPeriodic(){
        //closeGate.start();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        //autonomousCommand.start();
        updateStatus();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
        Timer.delay(.01);
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //autonomousCommand.cancel();
        updateStatus();
        //trackingOff.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
        Timer.delay(.01);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void updateStatus(){
        //CommandBase.compUtil.updateStatus();
        //CommandBase.liftUtil.updateStatus();
        Robot.angleUtil.updateStatus();
        Robot.motorUtil.updateStatus();
        Robot.shotUtil.updateStatus();
        Robot.angleUtil.updateStatus();
        Robot.targetsUtil.updateStatus();
        //CommandBase.driveUtil.updateStatus();
    }
}
