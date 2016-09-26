/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.SMU_ManageMotor;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author marnold
 */
public class ShotMotorUtil extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
     Victor motor1, motor2;
     
    
    public ShotMotorUtil(){
        motor1 = new Victor(RobotMap.smu_MOTOR_1);
        motor2 = new Victor(RobotMap.smu_MOTOR_2);
        /*
        motor2.enableDeadbandElimination(true);
        motor2.setBounds(255, 225, 128, 50, 2);
        motor1.enableDeadbandElimination(true);
        motor1.setBounds(255, 225, 128, 50, 2);
        */
    }
    
    public void setMotor1(double speed){
        motor1.set(-speed);
    }
    public void setMotor2(double speed){
        motor2.set(-speed);
    }
    
    public boolean getMotorStatus(){
        if((Math.abs(motor1.get()) != 0.00) &&
           (Math.abs(motor2.get()) != 0.00)){
            return true;
        }else{
            return false;
        }
    }
    
    public void updateStatus(){
        //SmartDashboard.getNumber("shotMotor1", motor1.get());
        //SmartDashboard.getNumber("shotMotor2", motor2.get());
        SmartDashboard.putBoolean("Shot Motors Activated", getMotorStatus());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new SMU_ManageMotor());
    }
}
