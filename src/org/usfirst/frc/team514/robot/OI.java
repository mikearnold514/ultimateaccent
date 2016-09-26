
package org.usfirst.frc.team514.robot;

import org.usfirst.frc.team514.robot.commands.CG_Autonomous;
import org.usfirst.frc.team514.robot.commands.DRV_Arcade;
import org.usfirst.frc.team514.robot.commands.DRV_Tank;
import org.usfirst.frc.team514.robot.commands.LU_Down;
import org.usfirst.frc.team514.robot.commands.LU_Up;
import org.usfirst.frc.team514.robot.commands.SAU_Duck;
import org.usfirst.frc.team514.robot.commands.SAU_Forward;
import org.usfirst.frc.team514.robot.commands.SAU_GetDisks;
import org.usfirst.frc.team514.robot.commands.SAU_Reverse;
import org.usfirst.frc.team514.robot.commands.SU_Fire;
import org.usfirst.frc.team514.robot.commands.TU_TrackOff;
import org.usfirst.frc.team514.robot.commands.TU_TrackY;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    Joystick leftstick, rightstick, controller;
    JoystickButton Tank, Arcade, 
                   HoistUp, HoistDown, 
                   TrackingOff, Track1PT, Track2PT, Track3PT, 
                   Shoot, ShotMotorOn, ShotMotorOff, 
                   LoadDisks, Duck, 
                   AngleUp, AngleDown;
    
    //Robot Status Variables - Use these to report SubSystem Status across
    //Commands and SubSystems.  Remember to create Getter and Setter methods...
    boolean FireControl, ShotMotors, onTarget, MagControl, Chamber;
    
    
    //Buttons for Testing Only!
    /*JoystickButton AngleUp, AngleDown;
    JoystickButton GateOpen, GateClose;
    JoystickButton ShotMotorOn, ShotMotorOff;
    JoystickButton Shoot, DontShoot;
    */
    
    public OI(){
        // Instantiate Objects here!
        // Include key press events here!
        leftstick = new Joystick(RobotMap.LEFT_STICK);
        rightstick = new Joystick(RobotMap.RIGHT_STICK);
        controller = new Joystick(RobotMap.CONTROLLER);
        
        Tank = new JoystickButton(rightstick, RobotMap.TANK_MODE);
        Arcade = new JoystickButton(rightstick, RobotMap.ARCADE_MODE);
        HoistUp = new JoystickButton(rightstick, RobotMap.HOIST_UP);
        LoadDisks = new JoystickButton(controller, RobotMap.LOAD_MAG_ANGLE);
        
        HoistDown = new JoystickButton(leftstick, RobotMap.HOIST_DOWN);
        Duck = new JoystickButton(controller, RobotMap.DUCK_DOWN);
        
        
        
        AngleUp = new JoystickButton(controller, RobotMap.ANGLE_UP);
        AngleDown = new JoystickButton(controller, RobotMap.ANGLE_DOWN);
        ShotMotorOn = new JoystickButton(controller, RobotMap.SHOT_MOTOR_ON);
        ShotMotorOff = new JoystickButton(controller, RobotMap.SHOT_MOTOR_OFF);
        Shoot = new JoystickButton(controller, RobotMap.FIRE);
        TrackingOff = new JoystickButton(controller, RobotMap.TRACKING_OFF);
        Track1PT = new JoystickButton(controller, RobotMap.TARGET_1PT);
        Track2PT = new JoystickButton(controller, RobotMap.TARGET_2PT);
        Track3PT = new JoystickButton(controller, RobotMap.TARGET_3PT);
        
        
        //Buttons for Testing Only!!!
        //GateOpen = new JoystickButton(leftstick, 2);
        //GateClose = new JoystickButton(leftstick, 3);
        //DontShoot = new JoystickButton(rightstick, 5);
        
        
        Tank.whenPressed(new DRV_Tank());      
        Arcade.whenPressed(new DRV_Arcade());
        HoistUp.whenPressed(new LU_Up());
        
        HoistDown.whenPressed(new LU_Down());
        LoadDisks.whenPressed(new SAU_GetDisks());
        Duck.whenPressed(new SAU_Duck());
        
        //TeleOp Selections
        //SmartDashboard.putData("Set for Full Automatic", new SU_SetFullAuto());
        
        AngleUp.whileHeld(new SAU_Forward());
        AngleDown.whileHeld(new SAU_Reverse());
        //ShotMotorOn.whenPressed(new SMU_MotorOn());
        //ShotMotorOff.whenPressed(new SMU_MotorOff());
        Shoot.whenPressed(new SU_Fire());
        TrackingOff.whenPressed(new TU_TrackOff());
        Track1PT.whenPressed(new TU_TrackY(1));         //1PT Shot
        Track2PT.whenPressed(new TU_TrackY(2));         //2PT Shot
        Track3PT.whenPressed(new TU_TrackY(3));         //3PT Shot            

            //Buttons for Testing Only!!!
        //GateOpen.whenPressed(new MAG_OpenGate());
        //GateClose.whenPressed(new MAG_CloseGate());
        
        //AutonomousMode Selections
        SmartDashboard.putData("1PT Target", new CG_Autonomous(1));
        SmartDashboard.putData("2PT Target", new CG_Autonomous(2));
        SmartDashboard.putData("3PT Target", new CG_Autonomous(3));
        
        
    }
    
    //Put additional Methods here
    public Joystick getLeftstick(){
        return leftstick;
    }
    
    public Joystick getRightstick(){
        return rightstick;
    }
    
    public double getLeftX(){
        return leftstick.getX();
    }
    
    public double getLeftY(){
        return leftstick.getY();
    }
    
    public double getRightX(){
        return rightstick.getX();
    }
    
    public double getRightY(){
        return rightstick.getY();
    }
    
    public boolean getFireControl(){
        return this.FireControl;
    }
    
    public void setFireControl(boolean status){
        this.FireControl = status;
    }
    
    public void setShotMotors(boolean status){
        this.ShotMotors = status;
    }
    
    public boolean getShotMotors(){
        return this.ShotMotors;
    }
    
    public void setChamberStatus(boolean status){
        this.Chamber = status;
    }
    
    public boolean getChamberStatus(){
        return this.Chamber;
    }
    
    public void setMagControl(boolean status){
        this.MagControl = status;
    }
    
    public boolean getMagControl(){
        return this.MagControl;
    }
}

