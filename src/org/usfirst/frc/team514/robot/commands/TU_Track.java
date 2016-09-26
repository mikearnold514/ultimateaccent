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
public class TU_Track extends Command {
    int myTarget;
    
    public TU_Track(int target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.targetsUtil);
        requires(Robot.driveUtil);
        requires(Robot.angleUtil);
        this.myTarget = target;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.targetsUtil.setTarget(myTarget);
        Robot.driveUtil.driveTank(0.00, 0.00);
        Robot.angleUtil.disableMotor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.targetsUtil.manageTargets();
            if(Robot.targetsUtil.foundTarget()){
                    followX(Robot.targetsUtil.getXDirection(),
                    		Robot.targetsUtil.onTargetX());
                    followY(Robot.targetsUtil.getDistance(),
                    		Robot.targetsUtil.getYDirection(),
                    		Robot.targetsUtil.onTargetY());
            }else{
            	Robot.driveUtil.driveTank(0.00, 0.00);
            	Robot.angleUtil.disableMotor();
            }        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        oi.setTargetStatus(targetsUtil.onTarget());
        return Robot.targetsUtil.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveUtil.driveTank(0.00, 0.00);                
    	Robot.angleUtil.disableMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private void followY(double distance, boolean direction, boolean ontarget){
        
        if(ontarget){
        	Robot.angleUtil.disableMotor();
        }else{
            if(direction){
            	Robot.angleUtil.enableForward();
            }else{
            	Robot.angleUtil.enableReverse();
            }
        }
    }
        
    private void followX(boolean direction, boolean ontarget){
        //double cmx;
        double leftX = RobotMap.tu_LEFT_MOTOR;
        double rightX = RobotMap.tu_RIGHT_MOTOR;
        
        //cmx = targetsUtil.getTargetCMX();
        
        //If object is directly in front, then use only distance to calculate speed.
        if(ontarget){
            leftX = 0.00;
            rightX = 0.00;
        }else{
            //Object is to the left or right.  Need to square inputs to left or right
            //motor depending on if you are in reverse and which side the target is...
            if(direction){
                rightX = -rightX;
            }else{
                leftX = -leftX;
            }
        }
        Robot.driveUtil.driveTank(leftX, rightX);
    }
        
    private double CoerceToRange(double inputMin, double inputMax,
                                 double outputMin, double outputMax,
                                 double input) {
        // TODO code application logic here
        double inputCenter;
        double outputCenter;
        double scale, result;
        double output;
        
            /* Determine the center of the input range and output range */
            inputCenter = Math.abs(inputMax - inputMin) / 2 + inputMin;
            outputCenter = Math.abs(outputMax - outputMin) / 2 + outputMin;

            /* Scale the input range to the output range */
            scale = (outputMax - outputMin) / (inputMax - inputMin);

            /* Apply the transformation */
            result = (input + -inputCenter) * scale + outputCenter;

            /* Constrain to the output range */
            output = Math.max(Math.min(result, outputMax), outputMin);
            return output;
                        
    }

}