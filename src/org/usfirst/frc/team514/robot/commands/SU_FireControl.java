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
public class SU_FireControl extends Command {
    boolean status;
    public SU_FireControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shotUtil);
        this.status = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.shotUtil.dontfire();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //manageMag
            this.status = false;
            Robot.oi.setMagControl(Robot.shotUtil.getMagSensor());
            Robot.oi.setChamberStatus(Robot.shotUtil.getChamber());
        //Manage the loading of the frizbees and set fire control status
        if(Robot.shotUtil.getPin()){
            if(Robot.shotUtil.getChamber()){
                this.status = true;                
                Robot.shotUtil.closeGate();
            }else{
                this.status = false;
                Robot.shotUtil.openGate();
            }
        //Safety Check - make sure Shot Motors are On before Fireing!
            if(Robot.oi.getShotMotors()){
                Robot.oi.setFireControl(this.status);
            }else{
                Robot.oi.setFireControl(false);
            }
            
        }else{
            this.status = false;
            Robot.oi.setFireControl(false);
            Robot.shotUtil.closeGate();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.oi.getFireControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
