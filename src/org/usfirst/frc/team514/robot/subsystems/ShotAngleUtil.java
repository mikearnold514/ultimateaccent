/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.SAU_StopMotor;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author marnold
 */
public class ShotAngleUtil extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
        Relay angleMotor;
        DigitalInput upperLimit,lowerLimit;
        AnalogPotentiometer Pot;
        
    public ShotAngleUtil(){
        
        angleMotor = new Relay(RobotMap.sau_MOTOR_RELAY);
        upperLimit = new DigitalInput(RobotMap.sau_UPPER_LS);
        lowerLimit = new DigitalInput(RobotMap.sau_LOWER_LS);
        Pot = new AnalogPotentiometer(RobotMap.sau_POT);
        angleMotor.setDirection(Relay.Direction.kBoth);
        
    }
    public boolean getUpper() {
        //Invert the logic so it reads right relative to what
        //the switch is reading...
        boolean status;
        if(upperLimit.get()){
            status = false;
        }else{
            status = true;
        }
        return status;
    }
    
    public boolean getLower() {
        //Invert the logic so it reads right relative to what
        //the switch is reading...
        boolean status;
        if(lowerLimit.get()){
            status = false;
        }else{
            status = true;
        }
        return status;
    }
    
    public void enableForward(){
        if(getUpper()){
            disableMotor();
        }else{
            angleMotor.set(Relay.Value.kForward);            
        }
    }
    
    public void disableMotor(){
        angleMotor.set(Relay.Value.kOff);
    }
    
    public void enableReverse(){
        if(getLower()){
            disableMotor();
        }else{
            angleMotor.set(Relay.Value.kReverse);            
        }
    }
    
    public boolean isEnabled(){
        if(angleMotor.get() == Relay.Value.kOn){
            return true;
        }else{
            return false;
        }
    }
    
    public double getPot(){
        return Pot.pidGet();
    }
    
    public void updateStatus() {
            //SmartDashboard.putBoolean("Upper Limit", getUpper());
            //SmartDashboard.putBoolean("Lower Limit", getLower());
            SmartDashboard.putNumber("Shooter Angle", getPot());
            SmartDashboard.putNumber("Shooter Value", getPot());
            //SmartDashboard.putBoolean("Angle Motor Enabled", isEnabled());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new SAU_StopMotor());
    }
}
