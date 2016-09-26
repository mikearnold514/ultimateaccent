/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.DRV_Tank;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author marnold
 */
public class DriveUtil extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    RobotDrive drive;
    SpeedController leftVic, rightVic;
    boolean drivemode;    
    double left, right;
    
    public DriveUtil(){
        leftVic = new Victor(RobotMap.drv_LEFT_MOTOR);
        rightVic = new Victor(RobotMap.drv_RIGHT_MOTOR);
        drive = new RobotDrive(leftVic, rightVic);
        drivemode = true;
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);        
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        this.left = 0.00;
        this.right = 0.00;
    }
    
    public void driveTank(double leftY, double rightY){
        drive.tankDrive(leftY, rightY);
        setDrivemode(true);
        this.left = leftY;
        this.right = rightY;
    }
    
    public void driveTank(Joystick left, Joystick right) {
        drive.tankDrive(left, right);
        setDrivemode(true);
    }
    
    public void driveArcade(double rightY, double rightX) {
        drive.arcadeDrive(rightY, rightX);
        setDrivemode(false);
    }
    
    public void driveArcade(Joystick right){
        drive.arcadeDrive(right);
        setDrivemode(false);
    }
    
    public boolean getDrivemode(){
        return this.drivemode;
    }
    
    private void setDrivemode(boolean drivemode) {
        this.drivemode = drivemode;
    }

    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DRV_Tank());
    }
    
    public void updateStatus(){
        //SmartDashboard.putNumber("Left Motor", this.left);
        //SmartDashboard.putNumber("Right Motor", this.right);
    }
}
