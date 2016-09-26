/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author marnold
 */
public class SAU_Reverse extends Command {
    
    public SAU_Reverse() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.angleUtil);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.angleUtil.getLower()){
            Robot.angleUtil.disableMotor();
        }else{
            Robot.angleUtil.enableReverse();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.angleUtil.getLower();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
