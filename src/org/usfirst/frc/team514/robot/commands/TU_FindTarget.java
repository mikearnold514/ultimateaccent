/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Team 514
 */
public class TU_FindTarget extends Command {
    int myTarget;
    boolean done;
    
    public TU_FindTarget(int target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.targetsUtil);
       // requires(driveUtil);
        requires(Robot.angleUtil);
        this.myTarget = target;
        this.done = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.targetsUtil.setTarget(myTarget);
       // driveUtil.driveTank(0.00, 0.00);
        Robot.angleUtil.disableMotor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       // if(this.myTarget != 3){
        //targetsUtil.manageTargets();
            if(Robot.targetsUtil.foundTarget()){
                Robot.angleUtil.disableMotor();
                this.done = true;
            }else{
                Robot.angleUtil.enableReverse();
            }
       // }else{
         //   this.done = true;
        }
    //}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        oi.setTargetStatus(targetsUtil.onTargetY());
        return this.done;
        
    }

    // Called once after isFinished returns true
    protected void end() {
        //driveUtil.driveTank(0.00, 0.00);                
        Robot.angleUtil.disableMotor();
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    
}