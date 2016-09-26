/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Team 514
 */
public class SAU_AutoDuck extends Command {
    int myMode;
    public SAU_AutoDuck(int mode) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.targetsUtil);
        requires(Robot.angleUtil);
        this.myMode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.targetsUtil.manageTargets();
        //if(myMode != 3){
            if(Robot.angleUtil.getPot() > (RobotMap.sau_DUCK - RobotMap.sau_WINDOW)){
                Robot.angleUtil.enableReverse();
            }
            if(Robot.angleUtil.getPot() < (RobotMap.sau_DUCK + RobotMap.sau_WINDOW)){
                Robot.angleUtil.enableForward();
            }
        //}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean status = false;
        if(Robot.angleUtil.getUpper() || 
           Robot.angleUtil.getLower()){
            Robot.angleUtil.disableMotor();
            status = true;
        }
        if((Robot.angleUtil.getPot() > (RobotMap.sau_DUCK - RobotMap.sau_WINDOW)) && 
           (Robot.angleUtil.getPot() < (RobotMap.sau_DUCK + RobotMap.sau_WINDOW))){
            status = true;
        }
        //if(this.myMode == 3){
        //    status = true;
        //}
        if(Robot.targetsUtil.foundTarget()){
            status = true;
        }
        return status;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
