/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author marnold
 */
public class LiftUtil extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Solenoid lifter;
    
    public LiftUtil(){
        lifter = new Solenoid(RobotMap.lu_LIFTER);
        
    }
    
    public void enableLift(){
        lifter.set(true);
    }
    
    public void disableLift(){
        lifter.set(false);
    }
    
    public void updateStatus(){
        //SmartDashboard.putBoolean("LiftStatus", lifter.get());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
