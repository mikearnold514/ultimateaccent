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
public class SMU_MotorOff extends Command {
    boolean done;
    
    public SMU_MotorOff() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.motorUtil);
        this.done = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.motorUtil.setMotor1(0.0);
        Robot.motorUtil.setMotor2(0.0);
        Robot.oi.setShotMotors(false);
        this.done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
