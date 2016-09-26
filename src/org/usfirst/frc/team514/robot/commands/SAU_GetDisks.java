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
 * @author marnold
 */
public class SAU_GetDisks extends Command {
    
    public SAU_GetDisks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.angleUtil);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.angleUtil.getPot() > (RobotMap.sau_FEEDER - RobotMap.sau_WINDOW)){
            Robot.angleUtil.enableReverse();
        }
        if(Robot.angleUtil.getPot() < (RobotMap.sau_FEEDER + RobotMap.sau_WINDOW)){
            Robot.angleUtil.enableForward();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean status = false;
        if(Robot.angleUtil.getUpper() || 
           Robot.angleUtil.getLower()){
            Robot.angleUtil.disableMotor();
            status = true;
        }
        if((Robot.angleUtil.getPot() > (RobotMap.sau_FEEDER - RobotMap.sau_WINDOW)) && 
           (Robot.angleUtil.getPot() < (RobotMap.sau_FEEDER + RobotMap.sau_WINDOW))){
            Robot.angleUtil.disableMotor();
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
