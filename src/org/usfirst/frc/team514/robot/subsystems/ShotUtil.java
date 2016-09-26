/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.SU_FireControl;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author marnold
 */
public class ShotUtil extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    Solenoid firingpin;
    DigitalInput chambersensor, pinsensor;
    DigitalInput magsensor;
    DoubleSolenoid maggate;
    
    
    public ShotUtil(){
        firingpin = new Solenoid(RobotMap.su_FIRING_PIN);
        chambersensor = new DigitalInput(RobotMap.su_CHAMBER_PS);
        pinsensor = new DigitalInput(RobotMap.su_FIRING_PIN_LS);
        magsensor = new DigitalInput(RobotMap.mag_PS);
        maggate = new DoubleSolenoid(RobotMap.mag_GATE_OPEN, RobotMap.mag_GATE_CLOSE);
        
    }
    
    public void fire(){
        firingpin.set(true);
    }
    
    public void dontfire(){
        firingpin.set(false);
    }
    
    public boolean getChamber(){
        boolean status;
        if(chambersensor.get()){
            status = false;
        }else{
            status = true;
        }
        return status;
    }
    
    public boolean getPin(){
        boolean status;
        if(pinsensor.get()){
            status = false;
        }else{
            status = true;
        }
        return status;
    }
    
    public boolean getMagSensor(){
        boolean status;
        if(magsensor.get()){
            status = false;
        }else{
            status = true;
        }
        return status;
    }
    
    public void closeGate(){
        maggate.set(DoubleSolenoid.Value.kForward);
    }
    
    public void openGate(){
        maggate.set(DoubleSolenoid.Value.kReverse);
    }

    
    
    public void updateStatus(){
        SmartDashboard.putBoolean("Magazine Full", getMagSensor());
        SmartDashboard.putBoolean("Chamber Full", getChamber());
        SmartDashboard.putBoolean("Firing Pin Ready", getPin());
        //SmartDashboard.putBoolean("Firing Pin", firingpin.get());
    }
    
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new SU_FireControl());
    }
}
