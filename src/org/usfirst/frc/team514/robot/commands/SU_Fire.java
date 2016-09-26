/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author marnold
 */
public class SU_Fire extends Command {
    boolean done;
    
    public SU_Fire() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shotUtil);
        this.done = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.oi.getFireControl()){
            Robot.oi.setFireControl(false);
            Robot.shotUtil.fire();
            Timer.delay(RobotMap.su_TIMER_DELAY);
        }
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
